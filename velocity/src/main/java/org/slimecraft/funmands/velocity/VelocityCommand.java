package org.slimecraft.funmands.velocity;

import com.velocitypowered.api.command.CommandSource;
import org.slimecraft.funmands.api.AbstractCommand;

import java.util.function.Consumer;

public class VelocityCommand extends AbstractCommand<
        CommandSource,
        CommandSource,
        VelocityContext,
        VelocityPreContext,
        VelocityFormat
        > {

    public VelocityCommand(String identifier) {
        super(identifier);
    }

    public VelocityCommand(String identifier, String description, String... aliases) {
        super(identifier, description, aliases);
    }

    @Override
    public VelocityFormat createFormat(String identifier, Consumer<VelocityContext> contextConsumer) {
        return new VelocityFormat(identifier, contextConsumer);
    }

    @Override
    public VelocityFormat createFormat(String identifier, Consumer<VelocityContext> contextConsumer, Consumer<VelocityPreContext> preContextConsumer) {
        return new VelocityFormat(identifier, contextConsumer, preContextConsumer);
    }
}
