package org.slimecraft.funmands.velocity;

import com.velocitypowered.api.command.CommandSource;
import org.slimecraft.funmands.api.AbstractFormat;

import java.util.function.Consumer;

public class VelocityFormat extends AbstractFormat<
        CommandSource,
        CommandSource,
        VelocityContext,
        VelocityPreContext
        > {
    public VelocityFormat(String identifier, Consumer<VelocityContext> contextConsumer) {
        super(identifier, contextConsumer);
    }

    public VelocityFormat(String identifier, Consumer<VelocityContext> contextConsumer, Consumer<VelocityPreContext> preContextConsumer) {
        super(identifier, contextConsumer, preContextConsumer);
    }
}
