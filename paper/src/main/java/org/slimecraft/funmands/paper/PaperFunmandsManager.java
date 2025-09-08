package org.slimecraft.funmands.paper;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.slimecraft.funmands.api.FunmandsManager;
import org.slimecraft.funmands.api.argument.ArgumentRegistry;
import org.slimecraft.funmands.api.CommandParser;

import java.util.Arrays;
import java.util.Collection;

public class PaperFunmandsManager implements FunmandsManager<PaperCommand> {
    private final ArgumentRegistry argumentRegistry;
    private final LifecycleEventManager<?> lifecycleEventManager;
    private final CommandParser<PaperCommand, LiteralCommandNode<CommandSourceStack>> parser;

    public PaperFunmandsManager(LifecycleEventManager<?> lifecycleEventManager) {
        this.argumentRegistry = new ArgumentRegistry();
        this.lifecycleEventManager = lifecycleEventManager;
        this.parser = new PaperCommandParser(this.argumentRegistry);
    }

    @Override
    public void registerCommand(PaperCommand command) {
        this.lifecycleEventManager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Collection<String> aliasesCollection = Arrays.asList(command.getAliases());
            command.getDescription().ifPresentOrElse(description -> {
                event.registrar().register(this.parser.parse(command), description, aliasesCollection);
            }, () -> {
                event.registrar().register(this.parser.parse(command), aliasesCollection);
            });
        });
    }

    @Override
    public ArgumentRegistry getArgumentRegistry() {
        return this.argumentRegistry;
    }
}
