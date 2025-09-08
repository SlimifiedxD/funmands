package org.slimecraft.funmands.api.argument.type;

import com.mojang.brigadier.arguments.LongArgumentType;
import org.slimecraft.funmands.api.argument.Argument;

public class LongArgument implements Argument<LongArgumentType> {
    @Override
    public LongArgumentType create(Object[] options) {
        return switch (options.length) {
            case 0 -> LongArgumentType.longArg();
            case 1 -> LongArgumentType.longArg((long) options[0]);
            case 2 -> LongArgumentType.longArg((long) options[0], (long) options[1]);
            default -> throw new IllegalStateException("Unexpected argument length: " + options.length);
        };
    }
}
