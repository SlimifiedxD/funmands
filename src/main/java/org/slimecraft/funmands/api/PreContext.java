package org.slimecraft.funmands.api;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public interface PreContext<S> {
    void addOptions(String argumentIdentifier, Object... options);

    Object[] getOptions(String argumentIdentifier);

    void addSuggestion(String argumentIdentifier, Function<S, Collection<Suggestion>> senderArgumentSuggestions);

    Map<String, Function<S, Collection<Suggestion>>> getSuggestions();

    Optional<Predicate<S>> getPredicate();

    void setPredicate(Predicate<S> predicate);
}
