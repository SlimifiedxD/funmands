package org.slimecraft.funmands.api.command;

import org.slimecraft.funmands.api.Context;
import org.slimecraft.funmands.api.Format;
import org.slimecraft.funmands.api.PreContext;

import java.util.function.Consumer;

public interface OptionalCommandBuilder<S, E, C extends Context<S, E>, P extends PreContext<S>, F extends Format<S, E, C, P>> {
    OptionalCommandBuilder<S, E, C, P, F> description(String description);

    OptionalCommandBuilder<S, E, C, P, F> aliases(String... aliases);

    OptionalCommandBuilder<S, E, C, P, F> addFormat(F format);

    OptionalCommandBuilder<S, E, C, P, F> addFormat(String format, Consumer<C> contextConsumer);

    OptionalCommandBuilder<S, E, C, P, F> addFormat(String format, Consumer<C> contextConsumer, Consumer<P> preContextConsumer);

    Command<C, P, F> build();
}
