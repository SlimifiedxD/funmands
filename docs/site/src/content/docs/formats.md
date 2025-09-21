---
title: Formats
---

A format is composed of a string, known as the `identifier`, a `context` and an optional `pre content`. These are parsed into a `Command`. Format strings have three unique types:

Empty Format:

```
""
```

> An empty string. This format is executed when the root of the command is executed.

Literal Format:

```
"foo bar baz"
```

> A string with unique identifiers that do not contain a ':'. This signifies that this is a literal.

Argument Format:

```
"foo:int bar:long baz:float"
```

> A string with unique identifiers before the ':'. This signifies that this is an argument of the `type` after the ':'.

Putting it together:

```
"foo bar:int baz qux:float quux corge:float"
```

> A string with both literals and arguments.

> Non-empty formats have their arguments delimited by a space.
