package org.slimecraft.funmands.api.argument.type;

import org.slimecraft.funmands.api.argument.Argument;
import org.slimecraft.funmands.api.argument.type.brig.UuidArgumentType;

public class UuidArgument implements Argument<UuidArgumentType> {
    @Override
    public UuidArgumentType create(Object[] options) {
        return new UuidArgumentType();
    }
}
