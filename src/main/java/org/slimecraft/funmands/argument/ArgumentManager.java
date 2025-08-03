package org.slimecraft.funmands.argument;

import com.mojang.brigadier.arguments.ArgumentType;

import java.util.ArrayList;
import java.util.List;

public class ArgumentManager {
    private final List<Argument<?>> arguments;

    public ArgumentManager(List<Argument<?>> arguments) {
        this.arguments = arguments;
    }

    public ArgumentManager() {
        this(new ArrayList<>());
    }

    public void addArgument(Argument<?> argument) {
        this.arguments.add(argument);
    }

    public ArgumentType<?> fromArgumentIdentifier(String identifier, Object... options) {
        for (final Argument<?> argument : this.arguments) {
            for (final String argumentIdentifier : argument.getIdentifiers()) {
                if (identifier.equals(argumentIdentifier)) {
                    return argument.getType(options);
                }
            }
        }
        throw new IllegalArgumentException("Invalid argument identifier: " + identifier + "!");
    }
}
