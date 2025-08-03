package org.slimecraft.funmands;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.MessageComponentSerializer;
import org.slimecraft.funmands.argument.ArgumentManager;

import java.util.*;
import java.util.function.Consumer;

public class CommandParser {
    private final ArgumentManager argumentManager;

    public CommandParser(ArgumentManager argumentManager) {
        this.argumentManager = argumentManager;
    }

    public LiteralCommandNode<CommandSourceStack> parse(Command command) {
        final LiteralArgumentBuilder<CommandSourceStack> tree = Commands.literal(command.getIdentifier());
        command.getFormats().forEach(format -> {
            final String identifier = format.getIdentifier();
            final Consumer<Context> contextConsumer = format.getContextConsumer();
            final Optional<Consumer<PreContext>> optPreContextConsumer = format.getPreContextConsumer();
            final PreContext preContext = new PreContext();
            if (identifier.isEmpty()) {
                tree.executes(context -> {
                    contextConsumer.accept(new Context(context.getSource(), new HashMap<>()));
                    return com.mojang.brigadier.Command.SINGLE_SUCCESS;
                });
                optPreContextConsumer.ifPresent(preContextConsumer -> {
                    preContextConsumer.accept(preContext);
                    preContext.getPredicate().ifPresent(predicate -> {
                        tree.requires(source -> {
                            return predicate.test(source.getSender());
                        });
                    });
                });
                return;
            }
            final String[] parts = identifier.split(" ");
            final int partsLength = parts.length;
            final int lastLength = partsLength - 1;
            final List<String> argumentNames = new ArrayList<>();
            ArgumentBuilder<CommandSourceStack, ?> last = null;
            for (int i = lastLength; i >= 0; i--) {
                ArgumentBuilder<CommandSourceStack, ?> node;
                final String part = parts[i];
                if (part.contains(":")) {
                    final String[] argumentParts = part.replaceAll("[<>]", "").split(":");
                    final String name = argumentParts[0];
                    argumentNames.add(name);
                    ArgumentType<?> type = this.argumentManager.fromArgumentIdentifier(argumentParts[1]);
                    node = Commands.argument(name, type);
                    format.getPreContextConsumer().ifPresent(preContextConsumer -> {
                        preContextConsumer.accept(preContext);
                            final RequiredArgumentBuilder<CommandSourceStack, ?> cast = (RequiredArgumentBuilder<CommandSourceStack, ?>) node;
                            cast.suggests((context, builder) -> {
                                preContext.getSuggestions().forEach((string, commandSenderCollectionFunction) -> {
                                    if (!string.equals(name)) {
                                        return;
                                    }
                                    final Collection<Suggestion> suggestions = commandSenderCollectionFunction.apply(context.getSource().getSender());
                                    suggestions.forEach(suggestion -> {
                                        final String value = suggestion.getValue();
                                        suggestion.getTooltip().ifPresentOrElse(component -> {
                                            builder.suggest(value, MessageComponentSerializer.message().serialize(component));
                                        }, () -> builder.suggest(value));
                                    });
                                });
                                return builder.buildFuture();
                            });
                    });
                } else {
                    node = Commands.literal(part);
                }
                if (last != null) {
                    node.then(last);
                }
                optPreContextConsumer.ifPresent(preContextConsumer -> {
                    preContextConsumer.accept(preContext);
                    preContext.getPredicate().ifPresent(predicate -> {
                        node.requires(source -> predicate.test(source.getSender()));
                    });
                });
                if (i == lastLength) {
                    node.executes(context -> {
                        final Map<String, Object> argsMap = new HashMap<>();
                        final CommandSourceStack source = context.getSource();
                        for (final String argumentName : argumentNames) {
                            final Object gotArgument = context.getArgument(argumentName, Object.class);
                            argsMap.put(argumentName, gotArgument);
                        }
                        contextConsumer.accept(new Context(source, argsMap));
                        return com.mojang.brigadier.Command.SINGLE_SUCCESS;
                    });
                }
                last = node;
            }
            tree.then(last);
        });
        return tree.build();
    }
}
