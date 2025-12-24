package org.slimecraft.funmands.paper.example;

import org.bukkit.plugin.java.JavaPlugin;
import org.slimecraft.funmands.api.argument.ArgumentRegistry;
import org.slimecraft.funmands.paper.PaperFunmandsManager;
import org.slimecraft.funmands.paper.command.PaperCommand;
import org.slimecraft.funmands.paper.example.argument.PersonArgument;
import org.slimecraft.funmands.paper.example.command.FlyCommand;
import org.slimecraft.funmands.paper.example.command.MyCoolCommand;

public final class FunmandsPaperExamplePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        final PaperFunmandsManager funmandsManager = new PaperFunmandsManager(this.getLifecycleManager());
        final ArgumentRegistry argumentRegistry = funmandsManager.getArgumentRegistry();

        argumentRegistry.register("person", new PersonArgument());

        funmandsManager.registerCommand(new MyCoolCommand());
        funmandsManager.registerCommand(new FlyCommand());
    }
}
