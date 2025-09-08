package org.slimecraft.funmands.api;

import org.slimecraft.funmands.api.argument.ArgumentRegistry;

public interface FunmandsManager<C extends Command<?>> {
    /**
     * Register a command.
     * @param command The command to register.
     */
    void registerCommand(C command);

    /**
     * Get the argument registry.
     */
    ArgumentRegistry getArgumentRegistry();
}
