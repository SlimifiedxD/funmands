package org.slimecraft.funmands.api.argument;

import org.slimecraft.funmands.api.argument.type.*;

import java.util.HashMap;
import java.util.Map;

/**
 * A registry for arguments that allows for identifying an {@link Argument} from a string.
 */
public final class ArgumentRegistry {
    /**
     * The mappings from a string to an argument.
     */
    private final Map<String, Argument<?>> arguments;

    public ArgumentRegistry() {
        this.arguments = new HashMap<>();
        this.arguments.put("int", new IntegerArgument());
        this.arguments.put("float", new FloatArgument());
        this.arguments.put("long", new LongArgument());
        this.arguments.put("double", new DoubleArgument());
        this.arguments.put("string", new StringArgument());
        this.arguments.put("bool", new BooleanArgument());
        this.arguments.put("uuid", new UuidArgument());
    }

    /**
     * Register an argument with a name and the argument type.
     */
    public void register(String name, Argument<?> argument) {
        this.arguments.put(name, argument);
    }

    /**
     * Get a {@link Argument} from its name.
     */
    @SuppressWarnings("unchecked")
    public <T extends Argument<?>> T get(String name) {
        return (T) this.arguments.get(name);
    }
}
