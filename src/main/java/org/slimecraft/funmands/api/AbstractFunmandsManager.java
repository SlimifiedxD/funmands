package org.slimecraft.funmands.api;

import org.slimecraft.funmands.api.argument.ArgumentRegistry;
import org.slimecraft.funmands.api.command.Command;

public abstract class AbstractFunmandsManager<C extends Command<?, ?, ?>> implements FunmandsManager<C> {
    private final ArgumentRegistry argumentRegistry;

    public AbstractFunmandsManager() {
        this.argumentRegistry = ArgumentRegistry.registry();
    }

    @Override
    public ArgumentRegistry getArgumentRegistry() {
        return this.argumentRegistry;
    }
}
