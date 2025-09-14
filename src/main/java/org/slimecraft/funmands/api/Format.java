package org.slimecraft.funmands.api;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Represents a format that can be added to a {@link Command}.
 * @param <S> The sender type.
 * @param <E> The executor type.
 */
public interface Format<S, E> {
    /**
     * Get the identifier of the format.
     */
    String getIdentifier();

    /**
     * Get the context consumer of the format, which is accepted after the command is executed.
     */
    Consumer<Context<S, E>> getContextConsumer();

    /**
     * Get the optional pre-context consumer of the format, which is accepted before the command is executed.
     */
    Optional<Consumer<PreContext<S>>> getPreContextConsumer();
}
