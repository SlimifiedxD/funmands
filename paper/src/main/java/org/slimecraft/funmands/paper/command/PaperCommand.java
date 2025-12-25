package org.slimecraft.funmands.paper.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.slimecraft.funmands.api.AbstractCommand;
import org.slimecraft.funmands.api.command.IdentifierCommandBuilder;
import org.slimecraft.funmands.paper.PaperContext;
import org.slimecraft.funmands.paper.PaperFormat;
import org.slimecraft.funmands.paper.PaperPreContext;

import java.util.function.Consumer;

public class PaperCommand extends AbstractCommand<
        CommandSender,
        Entity,
        PaperContext,
        PaperPreContext,
        PaperFormat
        > {
    public PaperCommand(String identifier, String description, String... aliases) {
        super(identifier, description, aliases);
    }

    public PaperCommand(String identifier) {
        super(identifier);
    }

    public static IdentifierCommandBuilder<PaperCommand, CommandSender, Entity, PaperContext, PaperPreContext, PaperFormat> builder() {
        return new PaperCommandBuilder();
    }

    @Override
    public PaperFormat createFormat(String identifier, Consumer<PaperContext> contextConsumer) {
        return new PaperFormat(identifier, contextConsumer);
    }

    @Override
    public PaperFormat createFormat(String identifier, Consumer<PaperContext> contextConsumer, Consumer<PaperPreContext> preContextConsumer) {
        return new PaperFormat(identifier, contextConsumer, preContextConsumer);
    }
}
