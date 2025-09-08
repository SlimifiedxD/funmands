package org.slimecraft.funmands.api;

import java.util.Optional;
import java.util.function.Consumer;

public interface Format<S, E> {
    String getIdentifier();

    Consumer<Context<S, E>> getContextConsumer();

    Optional<Consumer<PreContext<S>>> getPreContextConsumer();
}
