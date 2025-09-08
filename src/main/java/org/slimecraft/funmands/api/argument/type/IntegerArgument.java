package org.slimecraft.funmands.api.argument.type;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import org.slimecraft.funmands.api.argument.Argument;

public class IntegerArgument implements Argument<IntegerArgumentType> {
    @Override
    public IntegerArgumentType create(Object[] options) {
        return switch (options.length) {
            case 0 -> IntegerArgumentType.integer();
            case 1 -> IntegerArgumentType.integer((int) options[0]);
            case 2 -> IntegerArgumentType.integer((int) options[0], (int) options[1]);
            default -> throw new IllegalStateException("Unexpected argument length: " + options.length);
        };
    }
}
