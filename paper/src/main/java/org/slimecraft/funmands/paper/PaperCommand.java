package org.slimecraft.funmands.paper;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.slimecraft.funmands.api.AbstractCommand;
import org.slimecraft.funmands.api.Command;
import org.slimecraft.funmands.api.Context;
import org.slimecraft.funmands.api.PreContext;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

public class PaperCommand extends AbstractCommand<CommandSender,
        Entity,
        PaperFormat,
        Context<CommandSender, Entity>,
        PreContext<CommandSender>> {
    public PaperCommand(String identifier, String description, String... aliases) {
        super(identifier, description, aliases);
    }

    public PaperCommand(String identifier) {
        super(identifier);
    }

    @Override
    public PaperFormat createFormat(String identifier, Consumer<Context<CommandSender, Entity>> contextConsumer) {
        return new PaperFormat(identifier, contextConsumer);
    }

    @Override
    public PaperFormat createFormat(String identifier, Consumer<Context<CommandSender, Entity>> contextConsumer, Consumer<PreContext<CommandSender>> preContextConsumer) {
        return new PaperFormat(identifier, contextConsumer, preContextConsumer);
    }
}
