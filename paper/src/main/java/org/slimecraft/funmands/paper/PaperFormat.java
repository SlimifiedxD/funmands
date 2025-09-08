package org.slimecraft.funmands.paper;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.slimecraft.funmands.api.Context;
import org.slimecraft.funmands.api.Format;
import org.slimecraft.funmands.api.PreContext;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class PaperFormat implements Format<CommandSender, Entity> {
    private final String identifier;
    private final Consumer<Context<CommandSender, Entity>> contextConsumer;
    private Consumer<PreContext<CommandSender>> preContextConsumer;

    public PaperFormat(String identifier, Consumer<Context<CommandSender, Entity>> contextConsumer) {
        this.identifier = identifier;
        this.contextConsumer = contextConsumer;
    }

    public PaperFormat(String identifier, Consumer<Context<CommandSender, Entity>> contextConsumer, Consumer<PreContext<CommandSender>> preContextConsumer) {
        this.identifier = identifier;
        this.contextConsumer = contextConsumer;
        this.preContextConsumer = preContextConsumer;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public Consumer<Context<CommandSender, Entity>> getContextConsumer() {
        return this.contextConsumer;
    }

    @Override
    public Optional<Consumer<PreContext<CommandSender>>> getPreContextConsumer() {
        return Optional.ofNullable(this.preContextConsumer);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PaperFormat that = (PaperFormat) o;
        return Objects.equals(identifier, that.identifier) && Objects.equals(contextConsumer, that.contextConsumer) && Objects.equals(preContextConsumer, that.preContextConsumer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, contextConsumer, preContextConsumer);
    }
}
