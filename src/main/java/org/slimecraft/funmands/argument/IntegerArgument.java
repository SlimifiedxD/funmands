package org.slimecraft.funmands.argument;

import com.mojang.brigadier.arguments.IntegerArgumentType;

import java.util.Collection;
import java.util.List;

public class IntegerArgument implements Argument<IntegerArgumentType> {
    @Override
    public Collection<String> getIdentifiers() {
        return List.of("integer", "int");
    }

    @Override
    public IntegerArgumentType getType(Object... options) {
        return IntegerArgumentType.integer();
    }
}
