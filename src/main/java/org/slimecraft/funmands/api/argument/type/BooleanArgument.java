package org.slimecraft.funmands.api.argument.type;

import com.mojang.brigadier.arguments.BoolArgumentType;
import org.slimecraft.funmands.api.argument.Argument;

public class BooleanArgument implements Argument<BoolArgumentType> {
    @Override
    public BoolArgumentType create(Object[] options) {
        return BoolArgumentType.bool();
    }
}
