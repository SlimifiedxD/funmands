package org.slimecraft.funmands.velocity;

import com.velocitypowered.api.command.CommandSource;
import org.slimecraft.funmands.api.AbstractContext;

import java.util.Map;

public class VelocityContext extends AbstractContext<
        CommandSource,
        CommandSource
        > {
    private final CommandSource source;

    public VelocityContext(CommandSource source, Map<String, Object> arguments) {
        super(arguments);
        this.source = source;
    }

    @Override
    public CommandSource getSender() {
        return source;
    }

    @Override
    public CommandSource getExecutor() {
        return source;
    }
}
