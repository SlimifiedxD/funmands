package org.slimecraft.funmands.api.argument.type;

import com.mojang.brigadier.arguments.FloatArgumentType;
import org.slimecraft.funmands.api.argument.Argument;

public class FloatArgument implements Argument<FloatArgumentType> {
    @Override
    public FloatArgumentType create(Object[] options) {
        return switch (options.length) {
            case 0 -> FloatArgumentType.floatArg();
            case 1 -> FloatArgumentType.floatArg((float) options[0]);
            case 2 -> FloatArgumentType.floatArg((float) options[0], (float) options[1]);
            default -> throw new IllegalStateException("Unexpected argument length: " + options.length);
        };
    }
}
