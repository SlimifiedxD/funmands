package org.slimecraft.funmands.paper;

import com.mojang.brigadier.Message;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.MessageComponentSerializer;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.slimecraft.funmands.api.*;
import org.slimecraft.funmands.api.argument.ArgumentRegistry;

import java.util.*;

public class PaperCommandParser extends AbstractCommandParser<
        CommandSourceStack,
        CommandSender,
        Entity,
        PaperFormat,
        PaperContext,
        PaperPreContext,
        PaperCommand> {

    public PaperCommandParser(ArgumentRegistry argumentRegistry) {
        super(argumentRegistry);
    }

    @Override
    public PaperContext createContext(CommandSourceStack source, Map<String, Object> arguments) {
        return new PaperContext(source, arguments);
    }

    @Override
    public PaperPreContext createPreContext() {
        return new PaperPreContext();
    }

    @Override
    public CommandSender senderFromSource(CommandSourceStack source) {
        return source.getSender();
    }

    @Override
    public Message fromComponent(Component component) {
        return MessageComponentSerializer.message().serialize(component);
    }
}
