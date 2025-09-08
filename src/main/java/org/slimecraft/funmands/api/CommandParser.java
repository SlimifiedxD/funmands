package org.slimecraft.funmands.api;

import com.mojang.brigadier.tree.LiteralCommandNode;

public interface CommandParser<C extends Command<?>, T extends LiteralCommandNode<?>> {
    T parse(C command);
}
