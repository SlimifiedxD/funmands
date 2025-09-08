package org.slimecraft.funmands.paper.example.argument;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.argument.CustomArgumentType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class PersonArgumentType implements CustomArgumentType.Converted<@NotNull Person, @NotNull String> {
    @Override
    public @NotNull Person convert(@NotNull String nativeType) throws CommandSyntaxException {
        final String[] parts = nativeType.split(":");
        System.out.println(Arrays.toString(parts));
        return new Person(parts[0], Integer.parseInt(parts[1]), parts[2]);
    }

    @Override
    public ArgumentType<String> getNativeType() {
        return StringArgumentType.greedyString();
    }
}
