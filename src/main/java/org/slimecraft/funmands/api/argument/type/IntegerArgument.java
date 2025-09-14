package org.slimecraft.funmands.api.argument.type;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import org.slimecraft.funmands.api.argument.Argument;

import java.util.Optional;

public class IntegerArgument implements Argument<IntegerArgumentType> {
    public static final class Options {
        private Integer min;
        private Integer max;

        public Options() {
        }

        public Options(Integer min) {
            this.min = min;
        }

        public Options(Integer min, Integer max) {
            this.min = min;
            this.max = max;
        }

        public Optional<Integer> getMin() {
            return Optional.ofNullable(this.min);
        }

        public Optional<Integer> getMax() {
            return Optional.ofNullable((this.max));
        }
    }

    @Override
    public IntegerArgumentType create(Object[] options) {
        if (options.length == 0) {
            return IntegerArgumentType.integer();
        }
        final Options intOptions = (Options) options[0];
        final Optional<Integer> optMin = intOptions.getMin();
        final Optional<Integer> optMax = intOptions.getMax();
        final boolean minPresent = optMin.isPresent();
        final boolean maxPresent = optMax.isPresent();

        if (minPresent && maxPresent) {
            return IntegerArgumentType.integer(optMin.get(), optMax.get());
        } else if (minPresent) {
            return IntegerArgumentType.integer(optMin.get());
        } else {
            return IntegerArgumentType.integer();
        }
    }
}
