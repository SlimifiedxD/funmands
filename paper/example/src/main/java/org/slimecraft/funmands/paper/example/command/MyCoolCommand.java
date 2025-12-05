package org.slimecraft.funmands.paper.example.command;

import net.kyori.adventure.text.Component;
import org.slimecraft.funmands.api.FormatIdentifierBuilder;
import org.slimecraft.funmands.api.argument.ArgumentKey;
import org.slimecraft.funmands.paper.PaperCommand;
import org.slimecraft.funmands.paper.example.argument.Person;

public class MyCoolCommand extends PaperCommand {
    public MyCoolCommand() {
        super("cool");
        this.addFormat("", ctx -> {
            ctx.getExecutor().sendMessage(Component.text("Hello, from Funmands!"));
        });
        this.addFormat("do stuff yes please foo:int bar:long baz:float", ctx -> {
            ctx.getExecutor().sendMessage(Component.text((long) ctx.get("bar")));
        }, preCtx -> {
            preCtx.addOptions("bar", 3L, 5L);
        });
        this.addFormat("person:person", ctx -> {
            final Person person = ctx.get("person");
            ctx.getExecutor().sendMessage(Component.text(person.name()));
            ctx.getExecutor().sendMessage(Component.text(person.age()));
            ctx.getExecutor().sendMessage(Component.text(person.favouriteFood()));
        });
        this.addFormat(FormatIdentifierBuilder
                        .builder()
                        .literal("brigadier")
                        .literal("example")
                        .argument(new ArgumentKey<>("foo", Integer.class))
                        .argument(new ArgumentKey<>("bar", Float.class))
                        .argument(new ArgumentKey<>("baz", Person.class)),
                ctx -> {

                });
    }
}
