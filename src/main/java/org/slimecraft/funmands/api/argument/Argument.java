package org.slimecraft.funmands.api.argument;

import com.mojang.brigadier.arguments.ArgumentType;

/**
 * An argument that creates an {@link ArgumentType}
 * @param <T> The argument type.
 */
public interface Argument<T extends ArgumentType<?>> {
    T create(Object[] options);
}
