package org.slimecraft.funmands.paper;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.slimecraft.funmands.api.AbstractFormat;
import org.slimecraft.funmands.api.Context;
import org.slimecraft.funmands.api.Format;
import org.slimecraft.funmands.api.PreContext;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class PaperFormat extends AbstractFormat<CommandSender, Entity, PaperContext, PaperPreContext> {
    public PaperFormat(String identifier, Consumer<PaperContext> contextConsumer) {
        super(identifier, contextConsumer);
    }

    public PaperFormat(String identifier, Consumer<PaperContext> contextConsumer, Consumer<PaperPreContext> preContextConsumer) {
        super(identifier, contextConsumer, preContextConsumer);
    }
}