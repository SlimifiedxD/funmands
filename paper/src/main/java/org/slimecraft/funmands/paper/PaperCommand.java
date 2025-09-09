package org.slimecraft.funmands.paper;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.slimecraft.funmands.api.Command;
import org.slimecraft.funmands.api.Context;
import org.slimecraft.funmands.api.PreContext;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

public class PaperCommand implements Command<PaperFormat> {
    private final String identifier;
    private String description;
    private final String[] aliases;
    private final Set<PaperFormat> formats;

    public PaperCommand(String identifier, String description, String... aliases) {
        this.identifier = identifier;
        this.description = description;
        this.aliases = aliases;
        this.formats = new HashSet<>();
    }

    public PaperCommand(String identifier) {
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
    public void addFormat(PaperFormat format) {
        this.formats.add(format);
    }

    public void addFormat(String identifier, Consumer<Context<CommandSender, Entity>> contextConsumer) {
        this.formats.add(new PaperFormat(identifier, contextConsumer));
    }

    public void addFormat(String identifier, Consumer<Context<CommandSender, Entity>> contextConsumer, Consumer<PreContext<CommandSender>> preContextConsumer) {
        this.formats.add(new PaperFormat(identifier, contextConsumer, preContextConsumer));
    }

    @Override
    public Collection<PaperFormat> getFormats() {
        return this.formats;
    }
}
