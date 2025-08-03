package org.slimecraft.funmands.argument;

import com.mojang.brigadier.arguments.ArgumentType;

import java.util.Collection;

public interface Argument<T extends ArgumentType<?>> {
    Collection<String> getIdentifiers();

    T getType(Object... options);
}
