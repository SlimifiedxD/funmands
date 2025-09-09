package org.slimecraft.funmands.api;

import org.slimecraft.funmands.api.argument.ArgumentKey;

/**
 * Represents a context that is created after a command is executed.
 * @param <S> The sender type.
 * @param <E> The executor type.
 */
public interface Context<S, E> {
    /**
     * Get an argument from the context.
     * @param name The name of the argument.
     * @return The argument with the given name.
     * @param <T> The type to get.
     */
    <T> T get(String name);

    <T> T get(ArgumentKey<T> key);

    /**
     * Get the sender.
     */
    S getSender();

    /**
     * Get the executor.
     * @return
     */
    E getExecutor();
}
