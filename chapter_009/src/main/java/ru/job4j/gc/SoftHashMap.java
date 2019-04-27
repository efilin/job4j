package ru.job4j.gc;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class SoftHashMap<K, V> {

    private Map<K, SoftReference<V>> hashMap = new HashMap<>();


    public V put(K key, V value) {
        SoftReference<V> result = this.hashMap.put(key, new SoftReference<>(value));
        return result != null ? result.get() : null;
    }

    public V get(K key) {
        SoftReference<V> result = this.hashMap.get(key);
        return result != null ? result.get() : null;
    }
}
