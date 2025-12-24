package org.slimecraft.funmands.velocity;

import com.mojang.brigadier.Message;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.VelocityBrigadierMessage;
import net.kyori.adventure.text.Component;
import org.slimecraft.funmands.api.AbstractCommandParser;
import org.slimecraft.funmands.api.argument.ArgumentRegistry;
import org.slimecraft.funmands.velocity.command.VelocityCommand;

import java.util.Map;

public class VelocityCommandParser extends AbstractCommandParser<
        CommandSource,
        CommandSource,
        CommandSource,
        VelocityContext,
        VelocityPreContext,
        VelocityFormat,
        VelocityCommand
        > {
    public VelocityCommandParser(ArgumentRegistry argumentRegistry) {
        super(argumentRegistry);
    }

    @Override
    public VelocityContext createContext(CommandSource source, Map<String, Object> arguments) {
        return new VelocityContext(source, arguments);
    }

    @Override
    public VelocityPreContext createPreContext() {
        return new VelocityPreContext();
    }

    @Override
    public CommandSource senderFromSource(CommandSource source) {
        return source;
    }

    @Override
    public Message fromComponent(Component component) {
        return VelocityBrigadierMessage.tooltip(component);
    }
}
