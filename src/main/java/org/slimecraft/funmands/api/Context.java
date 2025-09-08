package org.slimecraft.funmands.api;

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
