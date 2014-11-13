package com.math.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 一一对应双向Map<br>
 * 注意:value值不可为null值，而且必须唯一<br>
 * 
 * @author 陈霖 2014-11-6
 * @param <K>
 * @param <V>
 */
public class DoubleHashMap<K, V> implements Map<K, V>, Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5328734531239842957L;
	/**
	 * key值映射
	 */
	private Map<K, Entry<K, V>> keyEntry = new ConcurrentHashMap<K, Entry<K, V>>();
	/**
	 * value值映射
	 */
	private Map<V, Entry<K, V>> valueEntry = new ConcurrentHashMap<V, Entry<K, V>>();

	@Override
	public int size() {
		return keyEntry.size();
	}

	@Override
	public boolean isEmpty() {
		return keyEntry.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return keyEntry.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return valueEntry.containsKey(value);
	}

	@Override
	public V get(Object key) {
		return keyEntry.get(key).getValue();
	}

	/**
	 * @param value
	 * @return K
	 */
	public K getKey(Object value) {
		return valueEntry.get(value).getKey();
	}

	@Override
	public V put(K key, V value) {
		if (key != null && value != null) {
			DEntry<K, V> entry = new DEntry<K, V>(key, value);
			keyEntry.put(key, entry);
			valueEntry.put(value, entry);
		}
		return null;
	}

	@Override
	public V remove(Object key) {
		Entry<K, V> entry = keyEntry.remove(key);
		if (entry != null) {
			valueEntry.remove(entry.getValue());
			return entry.getValue();
		}
		return null;
	}

	/**
	 * @param value
	 * @return K
	 */
	public K removeValue(Object value) {
		Entry<K, V> entry = valueEntry.remove(value);
		if (entry != null) {
			keyEntry.remove(entry.getKey());
			return entry.getKey();
		}
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
			put(e.getKey(), e.getValue());
		}
	}

	@Override
	public void clear() {
		keyEntry.clear();
		valueEntry.clear();
	}

	@Override
	public Set<K> keySet() {
		return keyEntry.keySet();
	}

	@Override
	public Collection<V> values() {
		return valueEntry.keySet();
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return (Set<Entry<K, V>>) keyEntry.values();
	}

	/**
	 * simple entry
	 * 
	 * @param <K>
	 * @param <V>
	 */
	private static class DEntry<K, V> implements Entry<K, V>, Serializable {

		/**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 916055187633811382L;
		/**
		 * key
		 */
		private final K key;
		/**
		 * value
		 */
		private final V value;

		/**
		 * @param key
		 * @param value
		 */
		public DEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			throw new UnsupportedOperationException();
		}

		@SuppressWarnings("rawtypes")
		public boolean equals(Object o) {
			if (o == null || !(o instanceof Map.Entry)) {
				return false;
			}
			Map.Entry e = (Map.Entry) o;
			return this.key.equals(e.getKey()) && this.value.equals(e.getValue());
		}

		public int hashCode() {
			return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
		}

		public String toString() {
			return key + "=" + value;
		}

	}
}
