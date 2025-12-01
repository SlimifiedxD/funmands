package org.slimecraft.funmands.paper;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.ArgumentResolver;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.slimecraft.funmands.api.AbstractContext;
import org.slimecraft.funmands.api.Context;
import org.slimecraft.funmands.api.argument.ArgumentKey;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class PaperContext extends AbstractContext<CommandSender, Entity> {
    private final CommandSourceStack source;

    public PaperContext(CommandSourceStack source, Map<String, Object> arguments) {
        super(arguments);
        this.source = source;
    }

    @Override
    public <T> T get(String name) {
        final T value = super.get(name);
        if (value instanceof final ArgumentResolver<?> resolver) {
            try {
                if (resolver.resolve(source) instanceof List<?> list) {
                    if (list.size() == 1) {
                        return (T) list.getFirst();
                    } else {
                        return (T) list;
                    }
                }
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
            }
        }
        return super.get(name);
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
