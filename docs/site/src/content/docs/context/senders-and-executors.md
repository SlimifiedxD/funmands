---
title: Senders & Executors
---

| **Sender**                                                                          | **Executor**                                                                                        |
| ----------------------------------------------------------------------------------- | --------------------------------------------------------------------------------------------------- |
| The thing that _sent_ the command. This can be a player, console, or command block. | The thing that _executed_ the command. This can be any entity in Minecraft, or sometimes even null. |
| _Usually_ the same as executor, unless the `/execute` command was used.             | Only exists because of the `/execute` command.                                                      |
