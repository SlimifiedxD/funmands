package org.slimecraft.funmands.paper.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.slimecraft.funmands.api.command.Command;
import org.slimecraft.funmands.api.command.CommandBuilder;
import org.slimecraft.funmands.paper.PaperContext;
import org.slimecraft.funmands.paper.PaperFormat;
import org.slimecraft.funmands.paper.PaperPreContext;

public class PaperCommandBuilder extends CommandBuilder<CommandSender, Entity, PaperContext, PaperPreContext, PaperFormat> {
    @Override
    public Command<PaperContext, PaperPreContext, PaperFormat> create(String identifier) {
        return new PaperCommand(identifier);
    }

    @Override
    public Command<PaperContext, PaperPreContext, PaperFormat> create(String identifier, String description, String... aliases) {
        return new PaperCommand(identifier, description, aliases);
    }
}
