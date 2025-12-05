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
    private final Map<Class<?>, String> argumentClasses;

    private ArgumentRegistry() {
        this.arguments = new HashMap<>();
        this.argumentClasses = new HashMap<>();
        this.register("int", new IntegerArgument());
        this.register("float", new FloatArgument());
        this.register("long", new LongArgument());
        this.register("double", new DoubleArgument());
        this.register("string", new StringArgument());
        this.register("bool", new BooleanArgument());
        this.register("uuid", new UuidArgument());
    }

    private static final class Singleton {
        public static final ArgumentRegistry INSTANCE = new ArgumentRegistry();
    }

    public static ArgumentRegistry registry() {
        return Singleton.INSTANCE;
    }

    /**
     * Register an argument with a name and the argument type.
     */
    public void register(String name, Argument<?> argument) {
        this.arguments.put(name, argument);
        this.argumentClasses.put(argument.create(new Object[]{}).getClass(), name);
    }

    /**
     * Get a {@link Argument} from its name.
     */
    @SuppressWarnings("unchecked")
    public <T extends Argument<?>> T get(String name) {
        return (T) this.arguments.get(name);
    }

    public String getArgumentName(Class<?> clazz) {
        return this.argumentClasses.get(clazz);
    }
}
