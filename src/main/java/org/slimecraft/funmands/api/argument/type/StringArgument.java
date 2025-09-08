package org.slimecraft.funmands.api.argument.type;

import com.mojang.brigadier.arguments.StringArgumentType;
import org.slimecraft.funmands.api.argument.Argument;

public class StringArgument implements Argument<StringArgumentType> {
    @Override
    public StringArgumentType create(Object[] options) {
        final StringArgumentType.StringType type = (StringArgumentType.StringType) options[0];
        return switch (type) {
            case SINGLE_WORD -> StringArgumentType.word();
            case QUOTABLE_PHRASE -> StringArgumentType.string();
            case GREEDY_PHRASE -> StringArgumentType.greedyString();
        };
    }
}
