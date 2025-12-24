package org.slimecraft.funmands.api.command;

import org.slimecraft.funmands.api.Context;
import org.slimecraft.funmands.api.Format;
import org.slimecraft.funmands.api.PreContext;

import java.util.function.Consumer;

public abstract class CommandBuilder<T extends Command<C, P, F>, S, E, C extends Context<S, E>, P extends PreContext<S>, F extends Format<S, E, C, P>> implements IdentifierCommandBuilder<T, S, E, C, P, F>, OptionalCommandBuilder<T, S, E, C, P, F> {
    private String identifier;
    private String description;
    private String[] aliases = new String[0];
    private T command;

    protected abstract T create(String identifier);

    protected abstract T create(String identifier, String description, String... aliases);

    @Override
    public OptionalCommandBuilder<T, S, E, C, P, F> identifier(String identifier) {
        this.identifier = identifier;
        command = create(identifier);
        return this;
    }

    @Override
    public OptionalCommandBuilder<T, S, E, C, P, F> description(String description) {
        this.description = description;
        command = create(identifier, description, aliases);
        return this;
    }

    @Override
    public OptionalCommandBuilder<T, S, E, C, P, F> aliases(String... aliases) {
        this.aliases = aliases;
        command = create(identifier, description, aliases);
        return this;
    }

    @Override
    public OptionalCommandBuilder<T, S, E, C, P, F> addFormat(F format) {
        command.addFormat(format);
        return this;
    }

    @Override
    public OptionalCommandBuilder<T, S, E, C, P, F> addFormat(String format, Consumer<C> contextConsumer) {
        command.addFormat(format, contextConsumer);
        return this;
    }

    @Override
    public OptionalCommandBuilder<T, S, E, C, P, F> addFormat(String format, Consumer<C> contextConsumer, Consumer<P> preContextConsumer) {
        command.addFormat(format, contextConsumer, preContextConsumer);
        return this;
    }

    @Override
    public T build() {
        return command;
    }
}
