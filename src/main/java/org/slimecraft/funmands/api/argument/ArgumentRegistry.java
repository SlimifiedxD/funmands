package org.slimecraft.funmands.api.argument;

import org.slimecraft.funmands.api.argument.type.*;

import java.util.HashMap;
import java.util.Map;

public final class ArgumentRegistry {
    private final Map<String, Argument<?>> arguments;

    public ArgumentRegistry() {
        this.arguments = new HashMap<>();
        this.arguments.put("int", new IntegerArgument());
        this.arguments.put("float", new FloatArgument());
        this.arguments.put("long", new LongArgument());
        this.arguments.put("double", new DoubleArgument());
        this.arguments.put("string", new StringArgument());
        this.arguments.put("bool", new BooleanArgument());
    }

    public void register(String name, Argument<?> argument) {
        this.arguments.put(name, argument);
    }

    @SuppressWarnings("unchecked")
    public <T extends Argument<?>> T get(String name) {
        return (T) this.arguments.get(name);
    }
}
