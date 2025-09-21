package org.slimecraft.funmands.api;

import java.util.Optional;
import java.util.function.Consumer;

public class AbstractFormat<S, E> implements Format<S, E> {
    private final String identifier;
    private final Consumer<Context<S, E>> contextConsumer;
    private Consumer<PreContext<S>> preContextConsumer;

    public AbstractFormat(String identifier, Consumer<Context<S, E>> contextConsumer) {
        this.identifier = identifier;
        this.contextConsumer = contextConsumer;
    }

    public AbstractFormat(String identifier, Consumer<Context<S, E>> contextConsumer, Consumer<PreContext<S>> preContextConsumer) {
        this.identifier = identifier;
        this.contextConsumer = contextConsumer;
        this.preContextConsumer = preContextConsumer;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public Consumer<Context<S, E>> getContextConsumer() {
        return this.contextConsumer;
    }

    @Override
    public Optional<Consumer<PreContext<S>>> getPreContextConsumer() {
        return Optional.ofNullable(this.preContextConsumer);
    }
}
