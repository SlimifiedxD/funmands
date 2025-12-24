package org.slimecraft.funmands.velocity.command;

import com.velocitypowered.api.command.CommandSource;
import org.slimecraft.funmands.api.command.Command;
import org.slimecraft.funmands.api.command.CommandBuilder;
import org.slimecraft.funmands.velocity.VelocityContext;
import org.slimecraft.funmands.velocity.VelocityFormat;
import org.slimecraft.funmands.velocity.VelocityPreContext;

public class VelocityCommandBuilder extends CommandBuilder<CommandSource, CommandSource, VelocityContext, VelocityPreContext, VelocityFormat> {
    @Override
    protected Command<VelocityContext, VelocityPreContext, VelocityFormat> create(String identifier) {
        return new VelocityCommand(identifier);
    }

    @Override
    protected Command<VelocityContext, VelocityPreContext, VelocityFormat> create(String identifier, String description, String... aliases) {
        return new VelocityCommand(identifier, description, aliases);
    }
}
