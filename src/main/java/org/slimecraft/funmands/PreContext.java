package org.slimecraft.funmands;

import com.mojang.brigadier.Message;
import io.papermc.paper.command.brigadier.MessageComponentSerializer;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class PreContext {
    private Predicate<CommandSender> predicate;
    private final Map<String, Function<CommandSender, Collection<Suggestion>>> suggestions;

    public PreContext() {
        this.suggestions = new HashMap<>();
    }

    public void withPredicate(Predicate<CommandSender> predicate) {
        this.predicate = predicate;
    }

    public void withSuggestions(String argument, Function<CommandSender, Collection<Suggestion>> senderSuggestionsFunction) {
        this.suggestions.put(argument, senderSuggestionsFunction);
    }

    public Map<String, Function<CommandSender, Collection<Suggestion>>> getSuggestions() {
        return this.suggestions;
    }

    public Optional<Predicate<CommandSender>> getPredicate() {
        return Optional.ofNullable(this.predicate);
    }
}
