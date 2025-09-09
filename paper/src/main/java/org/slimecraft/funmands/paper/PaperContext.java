package org.slimecraft.funmands.paper;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.slimecraft.funmands.api.Context;
import org.slimecraft.funmands.api.argument.ArgumentKey;

import java.util.Map;

@SuppressWarnings("unchecked")
public class PaperContext implements Context<CommandSender, Entity> {
    private final CommandSourceStack source;
    private final Map<String, Object> arguments;

    public PaperContext(CommandSourceStack source, Map<String, Object> arguments) {
        this.source = source;
        this.arguments = arguments;
    }

    @Override
    public <T> T get(String name) {
        return (T) this.arguments.get(name);
    }

    @Override
    public <T> T get(ArgumentKey<T> key) {
        return (T) this.arguments.get(key.name());
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
