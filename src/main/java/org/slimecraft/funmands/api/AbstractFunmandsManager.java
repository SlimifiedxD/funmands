package org.slimecraft.funmands.api;

import org.slimecraft.funmands.api.argument.ArgumentRegistry;

public abstract class AbstractFunmandsManager<C extends Command<?>> implements FunmandsManager<C> {
    private final ArgumentRegistry argumentRegistry;

    public AbstractFunmandsManager() {
        this.argumentRegistry = new ArgumentRegistry();
    }

    @Override
    public ArgumentRegistry getArgumentRegistry() {
        return this.argumentRegistry;
    }
}
