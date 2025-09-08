package org.slimecraft.funmands.api;

import net.kyori.adventure.text.Component;

import java.util.Optional;

/**
 * Represents a suggestion to an argument of a {@link Command}.
 */
public class Suggestion {
    /**
     * The name of the suggestion.
     */
    private final String name;
    /**
     * The tooltip that is shown when hovering over the suggestion.
     */
    private Component tooltip;

    /**
     * Create a suggestion from the given name.
     */
    public Suggestion(String name) {
        this.name = name;
    }

    /**
     * Create a suggestion from the given name and tooltip.
     */
    public Suggestion(String name, Component tooltip) {
        this.name = name;
        this.tooltip = tooltip;
    }

    /**
     * Get the name of the suggestion.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the optional tooltip of the suggestion.
     */
    public Optional<Component> getTooltip() {
        return Optional.ofNullable(this.tooltip);
    }
}
