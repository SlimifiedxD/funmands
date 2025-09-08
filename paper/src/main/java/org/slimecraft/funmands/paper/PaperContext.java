package org.slimecraft.funmands.paper;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.slimecraft.funmands.api.Context;

import java.util.Map;

public class PaperContext implements Context<CommandSender, Entity> {
    private final CommandSourceStack source;
    private final Map<String, Object> arguments;

    public PaperContext(CommandSourceStack source, Map<String, Object> arguments) {
        this.source = source;
        this.arguments = arguments;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String name) {
        return (T) this.arguments.get(name);
    }

    @Override
    public CommandSender getSender() {
        return this.source.getSender();
    }

    @Override
    public Entity getExecutor() {
        return this.source.getExecutor();
    }
}
