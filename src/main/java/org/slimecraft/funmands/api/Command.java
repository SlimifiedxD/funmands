package org.slimecraft.funmands.api;

import java.util.Collection;
import java.util.Optional;

/**
 * A command that can be registered by a {@link FunmandsManager}.
 */
public interface Command<F extends Format<?, ?>> {
    /**
     * Get the identifier of the command.
     */
    String getIdentifier();

    /**
     * Get the optional description of the command.
     */
    Optional<String> getDescription();

    /**
     * Get the aliases of the command.
     */
    String[] getAliases();

    /**
     * Add a format to the command.
     */
    void addFormat(F format);

    /**
     * Get all formats of the command.
     */
    Collection<F> getFormats();
}
