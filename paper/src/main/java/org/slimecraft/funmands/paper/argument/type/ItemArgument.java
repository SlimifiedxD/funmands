package org.slimecraft.funmands.paper.argument.type;

import com.mojang.brigadier.arguments.ArgumentType;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.registry.RegistryKey;
import org.bukkit.inventory.ItemType;
import org.slimecraft.funmands.api.argument.Argument;

public class ItemArgument implements Argument<ArgumentType<ItemType>> {
    @Override
    public ArgumentType<ItemType> create(Object[] options) {
        return ArgumentTypes.resource(RegistryKey.ITEM);
    }
}
