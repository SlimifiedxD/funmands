package org.slimecraft.funmands.api.argument.type;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import org.slimecraft.funmands.api.argument.Argument;

public class DoubleArgument implements Argument<DoubleArgumentType> {
    @Override
    public DoubleArgumentType create(Object[] options) {
        return switch (options.length) {
            case 0 -> DoubleArgumentType.doubleArg();
            case 1 -> DoubleArgumentType.doubleArg((double) options[0]);
            case 2 -> DoubleArgumentType.doubleArg((double) options[0], (double) options[1]);
            default -> throw new IllegalStateException("Unexpected argument length: " + options.length);
        };
    }
}
