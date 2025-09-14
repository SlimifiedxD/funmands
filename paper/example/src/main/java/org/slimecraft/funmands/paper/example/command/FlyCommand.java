package org.slimecraft.funmands.paper.example.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.entity.Player;
import org.slimecraft.funmands.api.Suggestion;
import org.slimecraft.funmands.api.argument.type.IntegerArgument;
import org.slimecraft.funmands.paper.PaperCommand;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FlyCommand extends PaperCommand {
    public FlyCommand() {
        super("fly", "I believe I can fly!", "f");

        this.addFormat("", ctx -> {
            if (!(ctx.getExecutor() instanceof final Player player)) {
                return;
            }
            player.setAllowFlight(!player.getAllowFlight());
            player.setFlying(player.getAllowFlight());
            player.sendMessage(MiniMessage.miniMessage().deserialize("<green>You are <flying> flying.</green>",
                    TagResolver.resolver("flying",
                            Tag.selfClosingInserting(Component.text(player.isFlying() ? "now" : "no longer")))));
        });
        this.addFormat("speed speed:int", ctx -> {
            if (!(ctx.getExecutor() instanceof final Player player)) {
                return;
            }
            final int speed = ctx.get("speed");
            if (Math.round(player.getFlySpeed() * 10) == speed) {
                player.sendMessage(MiniMessage.miniMessage().deserialize("<red>You are already at speed <speed></red>",
                        TagResolver.resolver("speed",
                                Tag.selfClosingInserting(Component.text(speed)))));
                return;
            }
            player.setFlySpeed((float) speed / 10);
            player.sendMessage(MiniMessage.miniMessage().deserialize("Your fly speed is now: <yellow><speed></yellow>.",
                    TagResolver.resolver("speed",
                            Tag.selfClosingInserting(Component.text(speed)))));
        }, preCtx -> {
            preCtx.addOptions("speed", new IntegerArgument.Options(1, 10));
            preCtx.addSuggestions("speed", sender -> {
                return IntStream.range(1, 11)
                        .mapToObj(value -> {
                            return new Suggestion(String.valueOf(value), MiniMessage.miniMessage().deserialize("A fly speed of <speed>",
                                    TagResolver.resolver("speed", Tag.selfClosingInserting(Component.text(value)))));
                        }).collect(Collectors.toSet());
            });
        });
    }
}
