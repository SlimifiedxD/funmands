package org.slimecraft.funmands.api.argument;

import com.mojang.brigadier.arguments.ArgumentType;

public interface Argument<T extends ArgumentType<?>> {
    T create(Object[] options);
}
