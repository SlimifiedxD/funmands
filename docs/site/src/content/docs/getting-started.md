---
title: Getting Started
---

In Funmands, creating a command is simple. I won't tell you, though, I'll show you.

```java
public class DocumentationCommand extends PaperCommand {
    public DocumentationCommand() {
        super("documentation", "I love documentation!", "docs");
        this.addFormat("", context -> {
            context.getExecutor().sendMessage(Component.text("Hello, world!"));
        });
    }
}
```

Just like that, you've created your first command with Funmands. Let's unpack what that little bit of code did:

Created a command with the name "documentation", the description "I love documentation!", and the aliases "docs"

When the root of the command is executed (which is just a fancy way of saying "/documentation"), it sends the message "Hello, World!" to the executor of the command.

To register it on Paper, simply use this code in the `JavaPlugin` class:

```java
final PaperFunmandsManager funmandsManager = new PaperFunmandsManager(this.getLifecycleManager());
funmandsManager.registerCommand(new DocumentationCommand());
```
