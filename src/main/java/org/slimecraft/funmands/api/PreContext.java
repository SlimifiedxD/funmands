package org.slimecraft.funmands.api;

import org.slimecraft.funmands.api.argument.ArgumentKey;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
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
     * Add options to an argument from its key.
     */
    void addOptions(ArgumentKey<?> key, Object... options);

    /**
     * Get all options of an argument from its identifier.
     */
    Object[] getOptions(String argumentIdentifier);

    /**
     * Get all options of an argument from its key.
     */
    Object[] getOptions(ArgumentKey<?> key);

    /**
     * Add suggestions to an argument identifier.
     */
    void addSuggestions(String argumentIdentifier, Function<S, Collection<Suggestion>> senderArgumentSuggestions);

    /**
     * Add suggestions to an argument key.
     */
    void addSuggestions(ArgumentKey<?> key, Function<S, Collection<Suggestion>> senderArgumentSuggestions);

    /**
     * Add async suggestions to an argument identifier.
     */
    void addAsyncSuggestions(String argumentIdentifier, Function<S, CompletableFuture<Collection<Suggestion>>> senderAsyncArgumentSuggestions);

    /**
     * Add async suggestions to an argument key.
     */
    void addAsyncSuggestions(ArgumentKey<?> key, Function<S, CompletableFuture<Collection<Suggestion>>> senderAsyncArgumentSuggestions);

    /**
     * Get all suggestions.
     */
    Map<String, Function<S, Collection<Suggestion>>> getSuggestions();

    Map<String, Function<S, CompletableFuture<Collection<Suggestion>>>> getAsyncSuggestions();

    /**
     * Get the optional predicate to execute this command.
     */
    Optional<Predicate<S>> getPredicate();

    /**
     * Set the predicate that is required to execute this command.
     */
    void setPredicate(Predicate<S> predicate);
}
