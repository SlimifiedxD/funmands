package org.slimecraft.funmands.api;

import org.slimecraft.funmands.api.argument.ArgumentRegistry;

public interface FunmandsManager<C extends Command<?>> {
    void registerCommand(C command);

    ArgumentRegistry getArgumentRegistry();
}
