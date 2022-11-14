package com.haha.util;

import org.apache.commons.collections4.map.CaseInsensitiveMap;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;

/**
 * @author iechen
 * @date 2022年11月14日
 */
public class MapUtil {
    private MapUtil() {

    }

    /**
     * 从map中获取值，但key的大小写不敏感
     */
    @Nullable
    public static Object getByCaseInsensitiveKey(@Nonnull Map<String, ?> map, @Nonnull String key) {
        Objects.requireNonNull(map);
        Objects.requireNonNull(key);
        return map.containsKey(key.toLowerCase()) ?
                map.get(key.toLowerCase()) : map.get(key.toUpperCase());
    }

    /**
     * 从map中获取string类型的值，但key的大小写不敏感
     */
    @Nullable
    public static String getStrByCaseInsensitiveKey(@Nonnull Map<String, ?> map, @Nonnull String key) {
        return (String) getByCaseInsensitiveKey(map, key);
    }

    /**
     * new有一个key的大小写不敏感的Map
     *
     * @param capacity map的容量
     * @return key的大小写不敏感的map
     */
    @Nonnull
    public static <K, V> CaseInsensitiveMap<K, V> newCaseInsensitiveMap(final int capacity) {
        return new CaseInsensitiveMap<>(capacity);
    }

    /**
     * 将一个map转换为key的大小写不敏感的Map
     *
     * @param map 源map
     * @return key的大小写不敏感的map
     */
    @Nonnull
    public static <K, V> CaseInsensitiveMap<K, V> newCaseInsensitiveMap(@Nonnull final Map<K, V> map) {
        Objects.requireNonNull(map);
        return new CaseInsensitiveMap<>(map);
    }
}
