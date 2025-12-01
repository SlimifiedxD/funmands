package org.slimecraft.funmands.paper.argument.type;

import com.mojang.brigadier.arguments.ArgumentType;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.slimecraft.funmands.api.argument.Argument;

public class PlayerArgument implements Argument<ArgumentType<PlayerSelectorArgumentResolver>> {
    @Override
    public ArgumentType<PlayerSelectorArgumentResolver> create(Object[] options) {
        return ArgumentTypes.player();
    }
}
