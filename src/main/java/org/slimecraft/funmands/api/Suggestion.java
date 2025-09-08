package org.slimecraft.funmands.api;

import net.kyori.adventure.text.Component;

import java.util.Optional;

public class Suggestion {
    private final String name;
    private Component tooltip;

    public Suggestion(String name) {
        this.name = name;
    }

    public Suggestion(String name, Component tooltip) {
        this.name = name;
        this.tooltip = tooltip;
    }

    public String getName() {
        return this.name;
    }

    public Optional<Component> getTooltip() {
        return Optional.ofNullable(this.tooltip);
    }
}
