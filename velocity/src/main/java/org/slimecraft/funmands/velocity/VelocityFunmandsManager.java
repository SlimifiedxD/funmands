package org.slimecraft.funmands.velocity;

import com.velocitypowered.api.command.BrigadierCommand;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import org.slimecraft.funmands.api.AbstractFunmandsManager;

public class VelocityFunmandsManager extends AbstractFunmandsManager<VelocityCommand> {
    private final CommandManager commandManager;
    private final VelocityCommandParser commandParser;

    public VelocityFunmandsManager(CommandManager commandManager) {
        this.commandManager = commandManager;
        this.commandParser = new VelocityCommandParser(this.getArgumentRegistry());
    }

    @Override
    public void registerCommand(VelocityCommand command) {
        final BrigadierCommand brigCommand = new BrigadierCommand(commandParser.parse(command));
        final CommandMeta meta = commandManager
                .metaBuilder(brigCommand)
                .aliases(command.getAliases())
                .build();
        commandManager.register(meta, brigCommand);
    }
}
