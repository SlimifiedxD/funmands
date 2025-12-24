package org.slimecraft.funmands.api;

import com.mojang.brigadier.tree.LiteralCommandNode;
import org.slimecraft.funmands.api.command.Command;

/**
 * A parser for commands.
 * @param <C> The {@link Command} type.
 * @param <T> The {@link LiteralCommandNode} type.
 */
public interface CommandParser<C extends Command<?, ?, ?>, T extends LiteralCommandNode<?>> {
    /**
     * Parse a command into a {@link LiteralCommandNode}
     * @param command The command to parse.
     */
    T parse(C command);
}
