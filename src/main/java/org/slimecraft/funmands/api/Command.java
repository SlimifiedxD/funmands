package org.slimecraft.funmands.api;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * A command that can be registered by a {@link FunmandsManager}.
 */
public interface Command<C extends Context<?, ?>, P extends PreContext<?>, F extends Format<?, ?>> {
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

    void addFormat(String format, Consumer<C> contextConsumer);

    void addFormat(String format, Consumer<C> contextConsumer, Consumer<P> preContextConsumer);

    /**
     * Get all formats of the command.
     */
    Collection<F> getFormats();
}
