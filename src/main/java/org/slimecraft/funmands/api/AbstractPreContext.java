package org.slimecraft.funmands.api;

import org.slimecraft.funmands.api.argument.ArgumentKey;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;

public class AbstractPreContext<S> implements PreContext<S> {
    private Predicate<S> predicate;
    private final Map<String, Function<S, Collection<Suggestion>>> suggestions;
    private final Map<String, Function<S, CompletableFuture<Collection<Suggestion>>>> asyncSuggestions;
    private final Map<String, Object[]> options;

    public AbstractPreContext() {
        this.suggestions = new HashMap<>();
        this.asyncSuggestions = new HashMap<>();
        this.options = new HashMap<>();
    }

    @Override
    public void addOptions(String argumentIdentifier, Object... options) {
        this.options.put(argumentIdentifier, options);
    }

    @Override
    public void addOptions(ArgumentKey<?> key, Object... options) {
        this.addOptions(key.name(), options);
    }

    @Override
    public Object[] getOptions(String argumentIdentifier) {
        return this.options.get(argumentIdentifier);
    }

    @Override
    public Object[] getOptions(ArgumentKey<?> key) {
        return this.getOptions(key.name());
    }

    @Override
    public void addSuggestions(String argumentIdentifier, Function<S, Collection<Suggestion>> senderArgumentSuggestions) {
        this.suggestions.put(argumentIdentifier, senderArgumentSuggestions);
    }

    @Override
    public void addSuggestions(ArgumentKey<?> key, Function<S, Collection<Suggestion>> senderArgumentSuggestions) {
        this.addSuggestions(key.name(), senderArgumentSuggestions);
    }

    @Override
    public void addAsyncSuggestions(String argumentIdentifier, Function<S, CompletableFuture<Collection<Suggestion>>> senderAsyncArgumentSuggestions) {
        this.asyncSuggestions.put(argumentIdentifier, senderAsyncArgumentSuggestions);
    }

    @Override
    public void addAsyncSuggestions(ArgumentKey<?> key, Function<S, CompletableFuture<Collection<Suggestion>>> senderAsyncArgumentSuggestions) {
        this.addAsyncSuggestions(key.name(), senderAsyncArgumentSuggestions);
    }

    @Override
    public Map<String, Function<S, Collection<Suggestion>>> getSuggestions() {
        return this.suggestions;
    }

    @Override
    public Map<String, Function<S, CompletableFuture<Collection<Suggestion>>>> getAsyncSuggestions() {
        return this.asyncSuggestions;
    }

    @Override
    public Optional<Predicate<S>> getPredicate() {
        return Optional.ofNullable(this.predicate);
    }

    @Override
    public void setPredicate(Predicate<S> predicate) {
        this.predicate = predicate;
    }
}
