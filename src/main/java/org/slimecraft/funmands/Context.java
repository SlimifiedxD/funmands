package org.slimecraft.funmands;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import java.util.Map;

public class Context {
    private final CommandSourceStack source;
    private final Map<String, Object> arguments;

    public Context(CommandSourceStack source, Map<String, Object> arguments) {
        this.source = source;
        this.arguments = arguments;
    }

    public <T> T get(String name) {
        try {
            return (T) this.arguments.get(name);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException();
        }
    }

    public CommandSender getSender() {
        return this.source.getSender();
    }

    public Entity getExecutor() {
        return this.source.getExecutor();
    }
}
