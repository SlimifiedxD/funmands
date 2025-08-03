package org.slimecraft.funmands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.slimecraft.funmands.argument.Argument;
import org.slimecraft.funmands.argument.ArgumentManager;
import org.slimecraft.funmands.argument.IntegerArgument;
import org.slimecraft.funmands.argument.string.GreedyStringArgument;
import org.slimecraft.funmands.argument.string.QuotedStringArgument;
import org.slimecraft.funmands.argument.string.WordStringArgument;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class CommandManager {
    private static final List<Argument<?>> DEFAULT_ARGUMENTS = new ArrayList<>(Arrays.asList(
            new IntegerArgument(),
            new GreedyStringArgument(),
            new QuotedStringArgument(),
            new WordStringArgument()
    ));

    private final LifecycleEventManager<?> lifecycleEventManager;
    private final CommandParser commandParser;
    private Consumer<Command> registrationConsumer;

    public CommandManager(LifecycleEventManager<?> lifecycleEventManager, CommandParser commandParser) {
        this.lifecycleEventManager = lifecycleEventManager;
        this.commandParser = commandParser;
    }

    public CommandManager(LifecycleEventManager<?> lifecycleEventManager) {
        this(lifecycleEventManager, new CommandParser(new ArgumentManager(DEFAULT_ARGUMENTS)));
    }

    public void register(Command command) {
        this.lifecycleEventManager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            final LiteralCommandNode<CommandSourceStack> parsed = this.commandParser.parse(command);
            final Collection<String> aliases = command.getAliases();
            command.getDescription().ifPresentOrElse(description -> {
                commands.register(parsed, description, aliases);
            }, () -> {
                commands.register(parsed, aliases);
            });
            if (this.registrationConsumer != null) {
                this.registrationConsumer.accept(command);
            }
        });
    }

    public void onRegister(Consumer<Command> registrationConsumer) {
        this.registrationConsumer = registrationConsumer;
    }
}
