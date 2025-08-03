package org.slimecraft.funmands;

import java.util.Optional;
import java.util.function.Consumer;

public class Format {
    private final String identifier;
    private final Consumer<Context> contextConsumer;
    private Consumer<PreContext> preContextConsumer;

    public Format(String identifier, Consumer<Context> contextConsumer) {
        this.identifier = identifier;
        this.contextConsumer = contextConsumer;
    }

    public Format(String identifier, Consumer<Context> contextConsumer, Consumer<PreContext> preContextConsumer) {
        this.identifier = identifier;
        this.contextConsumer = contextConsumer;
        this.preContextConsumer = preContextConsumer;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public Consumer<Context> getContextConsumer() {
        return this.contextConsumer;
    }

    public Optional<Consumer<PreContext>> getPreContextConsumer() {
        return Optional.ofNullable(preContextConsumer);
    }
}
