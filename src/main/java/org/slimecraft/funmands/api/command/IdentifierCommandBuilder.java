package org.slimecraft.funmands.api.command;

import org.slimecraft.funmands.api.Context;
import org.slimecraft.funmands.api.Format;
import org.slimecraft.funmands.api.PreContext;

public interface IdentifierCommandBuilder<T extends Command<C, P, F>, S, E, C extends Context<S, E>, P extends PreContext<S>, F extends Format<S, E, C, P>> {
    OptionalCommandBuilder<T, S, E, C, P, F> identifier(String identifier);
}
