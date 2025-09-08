package org.slimecraft.funmands.api;

public interface Context<S, E> {
    <T> T get(String name);

    S getSender();

    E getExecutor();
}
