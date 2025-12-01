package org.slimecraft.funmands.paper;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.slimecraft.funmands.api.AbstractFunmandsManager;
import org.slimecraft.funmands.api.FunmandsManager;
import org.slimecraft.funmands.api.argument.ArgumentRegistry;
import org.slimecraft.funmands.api.CommandParser;
import org.slimecraft.funmands.paper.argument.type.ItemArgument;
import org.slimecraft.funmands.paper.argument.type.PlayerArgument;

import java.util.Arrays;
import java.util.Collection;

public class PaperFunmandsManager extends AbstractFunmandsManager<PaperCommand> {
    private final LifecycleEventManager<?> lifecycleEventManager;
    private final CommandParser<PaperCommand, LiteralCommandNode<CommandSourceStack>> parser;

    public PaperFunmandsManager(LifecycleEventManager<?> lifecycleEventManager) {
        this.lifecycleEventManager = lifecycleEventManager;
        this.parser = new PaperCommandParser(this.getArgumentRegistry());
        this.getArgumentRegistry().register("item", new ItemArgument());
        this.getArgumentRegistry().register("player", new PlayerArgument());
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
}
