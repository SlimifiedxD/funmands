package org.slimecraft.funmands.paper;

import org.bukkit.command.CommandSender;
import org.slimecraft.funmands.api.PreContext;
import org.slimecraft.funmands.api.Suggestion;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;

public class PaperPreContext implements PreContext<CommandSender> {
    private Predicate<CommandSender> predicate;
    private final Map<String, Function<CommandSender, Collection<Suggestion>>> suggestions;
    private final Map<String, Function<CommandSender, CompletableFuture<Collection<Suggestion>>>> asyncSuggestions;
    private final Map<String, Object[]> options;

    public PaperPreContext() {
        this.suggestions = new HashMap<>();
        this.asyncSuggestions = new HashMap<>();
        this.options = new HashMap<>();
    }

    @Override
    public void addOptions(String argumentIdentifier, Object... options) {
        this.options.put(argumentIdentifier, options);
    }

    @Override
    public Object[] getOptions(String argumentIdentifier) {
        return this.options.get(argumentIdentifier);
    }

    @Override
    public void addSuggestions(String argumentIdentifier, Function<CommandSender, Collection<Suggestion>> senderArgumentSuggestions) {
        this.suggestions.put(argumentIdentifier, senderArgumentSuggestions);
    }

    @Override
    public void addAsyncSuggestions(String argumentIdentifier, Function<CommandSender, CompletableFuture<Collection<Suggestion>>> senderAsyncArgumentSuggestions) {
        this.asyncSuggestions.put(argumentIdentifier, senderAsyncArgumentSuggestions);
    }

    @Override
    public Map<String, Function<CommandSender, Collection<Suggestion>>> getSuggestions() {
        return this.suggestions;
    }

    @Override
    public Map<String, Function<CommandSender, CompletableFuture<Collection<Suggestion>>>> getAsyncSuggestions() {
        return this.asyncSuggestions;
    }

    @Override
    public Optional<Predicate<CommandSender>> getPredicate() {
        return Optional.ofNullable(this.predicate);
    }

    @Override
    public void setPredicate(Predicate<CommandSender> predicate) {
        this.predicate = predicate;
    }
}
