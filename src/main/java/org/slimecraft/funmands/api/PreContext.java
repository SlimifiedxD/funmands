package org.slimecraft.funmands.api;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Represents a context that is created before the command is executed.
 * @param <S> The sender type.
 */
public interface PreContext<S> {
    /**
     * Add options to an argument from its identifier.
     */
    void addOptions(String argumentIdentifier, Object... options);

    /**
     * Get all options of an argument from its identifier.
     */
    Object[] getOptions(String argumentIdentifier);

    /**
     * Add a suggestion to an argument identifier.
     */
    void addSuggestions(String argumentIdentifier, Function<S, Collection<Suggestion>> senderArgumentSuggestions);

    /**
     * Get all suggestions.
     */
    Map<String, Function<S, Collection<Suggestion>>> getSuggestions();

    /**
     * Get the optional predicate to execute this command.
     */
    Optional<Predicate<S>> getPredicate();

    /**
     * Set the predicate that is required to execute this command.
     */
    void setPredicate(Predicate<S> predicate);
}
