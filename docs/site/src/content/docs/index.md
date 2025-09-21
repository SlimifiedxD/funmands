---
title: Why Funmands?
---

Funmands was simply created to make command creation with [Brigadier](https://github.com/Mojang/brigadier) (Mojang's official command library for making commands pretty and input-sanitized) simpler. Existing alternatives such as [CommandAPI](https://github.com/CommandAPI/), [Cloud](https://cloud.incendo.org/), or [StrokkCommands](https://github.com/Strokkur424/StrokkCommands) solve different problems, but all serve the same goal: to make command creation easier. However, there is only so much simplicity you can have without dealing with the standard boilerplate of Java... until Funmands arrived.

Funmands has a declarative approach to commands, as opposed to the imperative ideology adopted by most frameworks; you fully declare your commands through strings, as if you were typing them in-game. Of course, this sacrifices type-safety in exchange for time, in which you can spend it being more productive instead of chaining builder after builder.

In summary, at the end of the day, commands are simply a means to an end, which is getting input from the user and doing something with it. Funmands was created to save you development time so you can spend it doing what matters.

> Funmands declares commands through **strings** instead of **builders** to save time.
