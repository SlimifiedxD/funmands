package org.slimecraft.funmands.api;

import org.slimecraft.funmands.api.argument.ArgumentKey;

import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class AbstractContext<S, E> implements Context<S, E> {
    private final Map<String, Object> arguments;

    public AbstractContext(Map<String, Object> arguments) {
        this.arguments = arguments;
    }

    @Override
    public <T> T get(String name) {
        return (T) this.arguments.get(name);
    }

    @Override
    public <T> T get(ArgumentKey<T> key) {
        return (T) this.arguments.get(key.name());
    }
}
