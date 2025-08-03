package org.slimecraft.funmands.argument.string;

import com.mojang.brigadier.arguments.StringArgumentType;
import org.slimecraft.funmands.argument.Argument;

import java.util.Collection;
import java.util.List;

public class WordStringArgument implements Argument<StringArgumentType> {
    @Override
    public Collection<String> getIdentifiers() {
        return List.of("quoted_string");
    }

    @Override
    public StringArgumentType getType(Object... options) {
        return StringArgumentType.word();
    }
}
