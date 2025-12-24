package org.slimecraft.funmands.api.command;

import org.slimecraft.funmands.api.Context;
import org.slimecraft.funmands.api.Format;
import org.slimecraft.funmands.api.PreContext;

public interface IdentifierCommandBuilder<S, E, C extends Context<S, E>, P extends PreContext<S>, F extends Format<S, E, C, P>> {
    OptionalCommandBuilder<S, E, C, P, F> identifier(String identifier);
}
