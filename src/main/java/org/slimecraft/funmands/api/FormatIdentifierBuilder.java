package org.slimecraft.funmands.api;

import org.slimecraft.funmands.api.argument.ArgumentKey;
import org.slimecraft.funmands.api.argument.ArgumentRegistry;

public final class FormatIdentifierBuilder {
    private final StringBuilder builder = new StringBuilder();

    private FormatIdentifierBuilder() {}

    public static FormatIdentifierBuilder builder() {
        return new FormatIdentifierBuilder();
    }

    public FormatIdentifierBuilder literal(String literal) {
        builder.append(literal).append(" ");
        return this;
    }

    public <T> FormatIdentifierBuilder argument(ArgumentKey<T> key) {
        builder.append(key.name()).append(":").append(ArgumentRegistry.registry().getArgumentName(key.clazz())).append(" ");
        return this;
    }

    public String build() {
        return builder.toString().trim();
    }
}
