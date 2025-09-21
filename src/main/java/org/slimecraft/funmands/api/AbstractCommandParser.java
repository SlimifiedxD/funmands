package org.slimecraft.funmands.api;

import com.mojang.brigadier.Message;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.kyori.adventure.text.Component;
import org.slimecraft.funmands.api.argument.ArgumentRegistry;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class AbstractCommandParser<S, CS, E, F extends Format<CS, E>, CO extends Context<CS, E>, P extends PreContext<CS>, C extends Command<F>> implements CommandParser<C, LiteralCommandNode<S>> {
    private final ArgumentRegistry argumentRegistry;

    public AbstractCommandParser(ArgumentRegistry argumentRegistry) {
        this.argumentRegistry = argumentRegistry;
    }

    public abstract CO createContext(S source, Map<String, Object> arguments);

    public abstract P createPreContext();

    public abstract CS senderFromSource(S source);

    public abstract Message fromComponent(Component component);

    @SuppressWarnings("unchecked")
    @Override
    public LiteralCommandNode<S> parse(C command) {
        LiteralArgumentBuilder<S> tree = LiteralArgumentBuilder.literal(command.getIdentifier());
        command.getFormats().forEach((format) -> {
            final String identifier = format.getIdentifier();
            final Consumer<Context<CS, E>> contextConsumer = format.getContextConsumer();
            final Optional<Consumer<PreContext<CS>>> optPreContextConsumer = format.getPreContextConsumer();
            final P preContext = this.createPreContext();
            final Map<String, Object> arguments = new HashMap<>();
            if (identifier.isEmpty()) {
                tree.executes((context) -> {
                    contextConsumer.accept(this.createContext(context.getSource(), arguments));
                    return 1;
                });
                optPreContextConsumer.ifPresent((preContextConsumer) -> {
                    preContextConsumer.accept(preContext);
                    preContext.getPredicate().ifPresent((predicate) -> tree.requires((source) -> predicate.test(this.senderFromSource(source))));
                });
            } else {
                final String[] parts = identifier.split(" ");
                final int partsLength = parts.length;
                final int lastLength = partsLength - 1;
                final List<String> argumentNames = new ArrayList<>();
                ArgumentBuilder<S, ?> last = null;

                for (int i = lastLength; i >= 0; --i) {
                    final String part = parts[i];
                    final ArgumentBuilder<S, ?> node;
                    if (part.contains(":")) {
                        final String[] argumentParts = part.split(":");
                        final String argumentIdentifier = argumentParts[0];
                        final String argumentType = argumentParts[1];
                        argumentNames.add(argumentIdentifier);
                        final ArgumentType<?> type;
                        if (format.getPreContextConsumer().isPresent()) {
                            format.getPreContextConsumer().get().accept(preContext);
                            final Object[] options = preContext.getOptions(argumentIdentifier);
                            type = this.argumentRegistry.get(argumentType).create(options != null ? options : new Object[]{});
                        } else {
                            type = this.argumentRegistry.get(argumentType).create(new Object[]{});
                        }
                        node = RequiredArgumentBuilder.argument(argumentIdentifier, type);
                        format.getPreContextConsumer().ifPresent((preContextConsumer) -> {
                            preContextConsumer.accept(preContext);
                            preContext.getPredicate().ifPresent((predicate) -> tree.requires(source -> predicate.test(this.senderFromSource(source))));
                            RequiredArgumentBuilder<S, ?> cast = (RequiredArgumentBuilder<S, ?>) node;
                            cast.suggests((context, builder) -> {
                                preContext.getSuggestions().forEach((string, commandSenderCollectionFunction) -> {
                                    if (string.equals(argumentIdentifier)) {
                                        Collection<Suggestion> suggestions = commandSenderCollectionFunction.apply(this.senderFromSource(context.getSource()));
                                        suggestions.forEach((suggestion) -> {
                                            String suggestionName = suggestion.getName();
                                            suggestion.getTooltip().ifPresentOrElse((component) -> builder.suggest(suggestionName, this.fromComponent(component)), () -> builder.suggest(suggestionName));
                                        });
                                    }
                                });
                                final Function<CS, CompletableFuture<Collection<Suggestion>>> asyncFn = preContext.getAsyncSuggestions().get(argumentIdentifier);

                                if (asyncFn == null) {
                                    return CompletableFuture.completedFuture(builder.build());
                                }

                                return asyncFn.apply(this.senderFromSource(context.getSource()))
                                        .thenApply(suggestions -> {
                                            suggestions.forEach((suggestion) -> {
                                                String suggestionName = suggestion.getName();
                                                suggestion.getTooltip().ifPresentOrElse((component) -> builder.suggest(suggestionName, this.fromComponent(component)), () -> builder.suggest(suggestionName));
                                            });
                                            return builder.build();
                                        });
                            });
                        });
                    } else {
                        node = LiteralArgumentBuilder.literal(part);
                    }

                    if (last != null) {
                        node.then(last);
                    }

                    if (i == lastLength) {
                        node.executes((context) -> {
                            S source = context.getSource();

                            for (String argumentName : argumentNames) {
                                Object gotArgument = context.getArgument(argumentName, Object.class);
                                arguments.put(argumentName, gotArgument);
                            }

                            contextConsumer.accept(this.createContext(source, arguments));
                            return 1;
                        });
                    }

                    last = node;
                }

                tree.then(last);
            }
        });
        return tree.build();
    }
}
