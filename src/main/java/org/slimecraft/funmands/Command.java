package org.slimecraft.funmands;

import java.util.*;
import java.util.function.Consumer;

public class Command {
    private final String identifier;
    private final List<Format> formats;
    private String description;
    private final Collection<String> aliases;

    public Command(String identifier) {
        this.identifier = identifier;
        this.formats = new ArrayList<>();
        this.aliases = new ArrayList<>();
    }

    public Command(String identifier, String description) {
        this.identifier = identifier;
        this.formats = new ArrayList<>();
        this.description = description;
        this.aliases = new ArrayList<>();
    }

    public Command(String identifier, String description, Collection<String> aliases) {
        this.identifier = identifier;
        this.formats = new ArrayList<>();
        this.description = description;
        this.aliases = aliases;
    }

    public void withFormat(String identifier, Consumer<Context> contextConsumer) {
        this.withFormat(new Format(identifier, contextConsumer));
    }

    public void withFormat(String identifier, Consumer<Context> contextConsumer, Consumer<PreContext> preContextConsumer) {
        this.withFormat(new Format(identifier, contextConsumer, preContextConsumer));
    }

    private void withFormat(Format format) {
        this.formats.add(format);
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<Format> getFormats() {
        return this.formats;
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(this.description);
    }

    public Collection<String> getAliases() {
        return this.aliases;
    }
}
