package org.slimecraft.funmands.paper;

import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.slimecraft.funmands.api.AbstractContext;
import org.slimecraft.funmands.api.Context;
import org.slimecraft.funmands.api.argument.ArgumentKey;

import java.util.Map;

@SuppressWarnings("unchecked")
public class PaperContext extends AbstractContext<CommandSender, Entity> {
    private final CommandSourceStack source;

    public PaperContext(CommandSourceStack source, Map<String, Object> arguments) {
        super(arguments);
        this.source = source;
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
