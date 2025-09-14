package org.slimecraft.funmands.api.argument.type.brig;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import java.util.UUID;

public class UuidArgumentType implements ArgumentType<UUID> {
    @Override
    public UUID parse(StringReader reader) throws CommandSyntaxException {
        return UUID.fromString(reader.readString());
    }
}
