package org.slimecraft.funmands.api;

import java.util.Collection;
import java.util.Optional;

public interface Command<F extends Format<?, ?>> {
    String getIdentifier();

    Optional<String> getDescription();

    String[] getAliases();

    void addFormat(F format);

    Collection<F> getFormats();
}
