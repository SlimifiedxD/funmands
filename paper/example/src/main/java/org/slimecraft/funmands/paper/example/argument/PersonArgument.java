package org.slimecraft.funmands.paper.example.argument;

import org.slimecraft.funmands.api.argument.Argument;

public class PersonArgument implements Argument<PersonArgumentType> {
    @Override
    public PersonArgumentType create(Object[] options) {
        return new PersonArgumentType();
    }
}
