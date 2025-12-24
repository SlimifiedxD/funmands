package org.slimecraft.funmands.api.command;

import org.slimecraft.funmands.api.Context;
import org.slimecraft.funmands.api.Format;
import org.slimecraft.funmands.api.PreContext;

import java.util.function.Consumer;

public interface OptionalCommandBuilder<T extends Command<C, P, F>, S, E, C extends Context<S, E>, P extends PreContext<S>, F extends Format<S, E, C, P>> {
    OptionalCommandBuilder<T, S, E, C, P, F> description(String description);

    OptionalCommandBuilder<T, S, E, C, P, F> aliases(String... aliases);

    OptionalCommandBuilder<T, S, E, C, P, F> addFormat(F format);

    OptionalCommandBuilder<T, S, E, C, P, F> addFormat(String format, Consumer<C> contextConsumer);

    OptionalCommandBuilder<T, S, E, C, P, F> addFormat(String format, Consumer<C> contextConsumer, Consumer<P> preContextConsumer);

    T build();
}
