package org.slimecraft.funmands.api;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

public abstract class AbstractCommand<S, E, C extends Context<S, E>, P extends PreContext<S>, F extends Format<S, E, C, P>> implements Command<C, P, F> {
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

    public abstract F createFormat(String identifier, Consumer<C> contextConsumer);

    public abstract F createFormat(String identifier, Consumer<C> contextConsumer, Consumer<P> preContextConsumer);

    @Override
    public void addFormat(F format) {
        this.formats.add(format);
    }

    @Override
    public void addFormat(String identifier, Consumer<C> contextConsumer) {
        this.addFormat(this.createFormat(identifier, contextConsumer));
    }

    public void addFormat(String identifier, Consumer<C> contextConsumer, Consumer<P> preContextConsumer) {
        this.addFormat(this.createFormat(identifier, contextConsumer, preContextConsumer));
    }

    public void addFormat(FormatIdentifierBuilder builder, Consumer<C> contextConsumer) {
        this.addFormat(builder.build(), contextConsumer);
    }

    public void addFormat(FormatIdentifierBuilder builder, Consumer<C> contextConsumer, Consumer<P> preContextConsumer) {
        this.addFormat(builder.build(), contextConsumer, preContextConsumer);
    }

    @Override
    public Collection<F> getFormats() {
        return this.formats;
    }
}
