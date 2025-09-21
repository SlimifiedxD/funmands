---
title: Context
---

Okay. You know what a format is, but you want to know how to **actually** do stuff when your command is executed. That's where the **Context** comes in. It has only one purpose: to facilitate your logic. It allows you to get the **sender** of a command, along with the **executor**, and all **arguments**.

```java
addFormat("", context -> {
    if (!(context.getSender() instanceof final Player player)) {
        return;
    }
    player.sendMessage(player.getName());
});
```

> A context simply provides the **logic** that runs when your command is executed.
