package org.slimecraft.funmands.api;

import java.util.Optional;
import java.util.function.Consumer;

public class AbstractFormat<S, E, C extends Context<S, E>, P extends PreContext<S>> implements Format<S, E, C, P> {
    private final String identifier;
    private final Consumer<C> contextConsumer;
    private Consumer<P> preContextConsumer;

    public AbstractFormat(String identifier, Consumer<C> contextConsumer) {
        this.identifier = identifier;
        this.contextConsumer = contextConsumer;
    }

    public AbstractFormat(String identifier, Consumer<C> contextConsumer, Consumer<P> preContextConsumer) {
        this.identifier = identifier;
        this.contextConsumer = contextConsumer;
        this.preContextConsumer = preContextConsumer;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public Consumer<C> getContextConsumer() {
        return this.contextConsumer;
    }

    @Override
    public Optional<Consumer<P>> getPreContextConsumer() {
        return Optional.ofNullable(this.preContextConsumer);
    }
}
