# Getting Started
How do I use it?
```java
public class GettingStartedCommand extends Command {
    public GettingStartedCommand() {
        super("gettingstarted");
        this.withFormat("", ctx -> {
            if (!(ctx.getSender() instanceof final Player player)) {
                return;
            }
            if (player.getGameMode() != GameMode.CREATIVE) {
                return;
            }
            player.sendMessage(Component
                    .text()
                    .content("You are in creative!")
                    .color(NamedTextColor.AQUA)
            );
        }); // An empty format will execute at /gettingstarted

        this.withFormat("<foo:int>", ctx -> {
            final int foo = ctx.get("foo");
            ctx.getSender().sendMessage(Component.text(foo));
        }); // A format that will execute at /gettingstarted <int>
    }
}
```
How do I register it?
```java
public class FunmandsExamplePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        final CommandManager commandManager = new CommandManager(this.getLifecycleManager());
        commandManager.register(new GettingStartedCommand());
    }
}
```
What if I want requirements?
```java
public class GettingStartedCommand extends Command {
    public GettingStartedCommand() {
        super("gettingstarted");
        this.withFormat("", ctx -> {
            if (!(ctx.getSender() instanceof final Player player)) {
                return;
            }
            if (player.getGameMode() != GameMode.CREATIVE) {
                return;
            }
            player.sendMessage(Component
                    .text()
                    .content("You are in creative!")
                    .color(NamedTextColor.AQUA)
            );
        }); // An empty format will execute at /gettingstarted

        this.withFormat("<foo:int>", ctx -> {
            final int foo = ctx.get("foo");
            ctx.getSender().sendMessage(Component.text(foo));
        }, preCtx -> {
            preCtx.withPredicate(sender -> {
                return sender.hasPermission("bar.baz");
            });
        }); // A format that will execute at /gettingstarted <int>
            // that requires a certain permission to not only see but execute
    }
}
```
What about suggestions? With tooltips?
```java
public class GettingStartedCommand extends Command {
    public GettingStartedCommand() {
        super("gettingstarted");
        this.withFormat("", ctx -> {
            if (!(ctx.getSender() instanceof final Player player)) {
                return;
            }
            if (player.getGameMode() != GameMode.CREATIVE) {
                return;
            }
            player.sendMessage(Component
                    .text()
                    .content("You are in creative!")
                    .color(NamedTextColor.AQUA)
            );
        }); // An empty format will execute at /gettingstarted

        this.withFormat("<foo:int>", ctx -> {
            final int foo = ctx.get("foo");
            ctx.getSender().sendMessage(Component.text(foo));
        }, preCtx -> {
            preCtx.withPredicate(sender -> {
                return sender.hasPermission("bar.baz");
            });
            preCtx.withSuggestions("foo", sender -> {
                if (!(sender instanceof final Player player)) {
                    return new ArrayList<>();
                }
                // some expensive database operation
                final List<String> homeNames = new ArrayList<>();
                homeNames.add("foo");
                homeNames.add("bar");
                homeNames.add("baz");
                return homeNames.stream().map(string -> {
                    return new Suggestion(string, Component
                            .text()
                            .content("Home: " + string)
                            .asComponent()
                    );
                }).toList();
            }); // We want to give foo these suggestions, and return a Collection<Suggestion> from a sender
        }); // A format that will execute at /gettingstarted <int>
        // that requires a certain permission to not only see but execute
    }
}
```