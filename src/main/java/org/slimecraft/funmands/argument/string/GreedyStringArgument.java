package org.slimecraft.funmands.argument.string;

import com.mojang.brigadier.arguments.StringArgumentType;
import org.slimecraft.funmands.argument.Argument;

import java.util.Collection;
import java.util.List;

public class GreedyStringArgument implements Argument<StringArgumentType> {
    @Override
    public Collection<String> getIdentifiers() {
        return List.of("string_greedy");
    }

    @Override
    public StringArgumentType getType(Object... options) {
        return StringArgumentType.greedyString();
    }
}
