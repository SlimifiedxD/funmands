package org.slimecraft.funmands.api;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AbstractCommand<S, E, F extends Format<S, E>, C extends Context<S, E>> implements Command<F> {
    private final String identifier;
    private String description;
    private final String[] aliases;
    private final Set<F> formats;

    public AbstractCommand(String identifier, String description, String... aliases) {
        this.identifier = identifier;
        this.description = description;
        this.aliases = aliases;
        this.formats = new HashSet<>();
    }

    public AbstractCommand(String identifier) {
        this.identifier = identifier;
        this.aliases = new String[]{};
        this.formats = new HashSet<>();
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.ofNullable(this.description);
    }

    @Override
    public String[] getAliases() {
        return this.aliases;
    }

    @Override
    public void addFormat(F format) {
        this.formats.add(format);
    }

    @Override
    public Collection<F> getFormats() {
        return this.formats;
    }
}
