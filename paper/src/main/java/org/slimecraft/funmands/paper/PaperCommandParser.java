package org.slimecraft.funmands.paper;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.MessageComponentSerializer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.slimecraft.funmands.api.CommandParser;
import org.slimecraft.funmands.api.Context;
import org.slimecraft.funmands.api.PreContext;
import org.slimecraft.funmands.api.Suggestion;
import org.slimecraft.funmands.api.argument.ArgumentRegistry;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

public class PaperCommandParser implements CommandParser<PaperCommand, LiteralCommandNode<CommandSourceStack>> {
    private final ArgumentRegistry argumentRegistry;

    public PaperCommandParser(ArgumentRegistry argumentRegistry) {
        this.argumentRegistry = argumentRegistry;
    }

    // WARNING: THE BELOW CODE IS EXTREMELY DOGSHIT.
    // IT WILL BE REFACTORED AT !?@#$?$%#@#$%^_(*
    @SuppressWarnings("unchecked")
    @Override
    public LiteralCommandNode<CommandSourceStack> parse(PaperCommand command) {
        LiteralArgumentBuilder<CommandSourceStack> tree = Commands.literal(command.getIdentifier());
        command.getFormats().forEach((format) -> {
            final String identifier = format.getIdentifier();
            final Consumer<Context<CommandSender, Entity>> contextConsumer = format.getContextConsumer();
            final Optional<Consumer<PreContext<CommandSender>>> optPreContextConsumer = format.getPreContextConsumer();
            final PaperPreContext preContext = new PaperPreContext();
            if (identifier.isEmpty()) {
                tree.executes((context) -> {
                    contextConsumer.accept(new PaperContext(context.getSource(), new HashMap<>()));
                    return 1;
                });
                optPreContextConsumer.ifPresent((preContextConsumer) -> {
                    preContextConsumer.accept(preContext);
                    preContext.getPredicate().ifPresent((predicate) -> tree.requires((source) -> predicate.test(source.getSender())));
                });
            } else {
                final String[] parts = identifier.split(" ");
                final int partsLength = parts.length;
                final int lastLength = partsLength - 1;
                final List<String> argumentNames = new ArrayList<>();
                ArgumentBuilder<CommandSourceStack, ?> last = null;

                for (int i = lastLength; i >= 0; --i) {
                    final String part = parts[i];
                    final ArgumentBuilder<CommandSourceStack, ?> node;
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
                        node = Commands.argument(argumentIdentifier, type);
                        format.getPreContextConsumer().ifPresent((preContextConsumer) -> {
                            preContextConsumer.accept(preContext);
                            RequiredArgumentBuilder<CommandSourceStack, ?> cast = (RequiredArgumentBuilder<CommandSourceStack, ?>) node;
                            cast.suggests((context, builder) -> {
                                preContext.getSuggestions().forEach((string, commandSenderCollectionFunction) -> {
                                    if (string.equals(argumentIdentifier)) {
                                        Collection<Suggestion> suggestions = commandSenderCollectionFunction.apply(context.getSource().getSender());
                                        suggestions.forEach((suggestion) -> {
                                            String suggestionName = suggestion.getName();
                                            suggestion.getTooltip().ifPresentOrElse((component) -> builder.suggest(suggestionName, MessageComponentSerializer.message().serialize(component)), () -> builder.suggest(suggestionName));
                                        });
                                    }
                                });
                                final Function<CommandSender, CompletableFuture<Collection<Suggestion>>> asyncFn = preContext.getAsyncSuggestions().get(argumentIdentifier);

                                if (asyncFn == null) {
                                    return CompletableFuture.completedFuture(builder.build());
                                }

                                return asyncFn.apply(context.getSource().getSender())
                                        .thenApply(suggestions -> {
                                            suggestions.forEach((suggestion) -> {
                                                String suggestionName = suggestion.getName();
                                                suggestion.getTooltip().ifPresentOrElse((component) -> builder.suggest(suggestionName, MessageComponentSerializer.message().serialize(component)), () -> builder.suggest(suggestionName));
                                            });
                                            return builder.build();
                                        });
                            });
                        });
                    } else {
                        node = Commands.literal(part);
                    }

                    if (last != null) {
                        node.then(last);
                    }

                    optPreContextConsumer.ifPresent((preContextConsumer) -> {
                        preContextConsumer.accept(preContext);
                        preContext.getPredicate().ifPresent((predicate) -> node.requires((source) -> predicate.test(source.getSender())));
                    });
                    if (i == lastLength) {
                        node.executes((context) -> {
                            Map<String, Object> argsMap = new HashMap<>();
                            CommandSourceStack source = context.getSource();

                            for (String argumentName : argumentNames) {
                                Object gotArgument = context.getArgument(argumentName, Object.class);
                                argsMap.put(argumentName, gotArgument);
                            }

                            contextConsumer.accept(new PaperContext(source, argsMap));
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
