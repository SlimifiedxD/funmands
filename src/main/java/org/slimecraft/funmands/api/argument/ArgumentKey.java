package org.slimecraft.funmands.api.argument;

/**
 */

/**
 * A type-safe way to retrieve arguments. See {@link }
 * @param name
 * @param clazz
 * @param <T> The type that this key stores.
 */
public record ArgumentKey<T>(String name, Class<T> clazz) {
}
