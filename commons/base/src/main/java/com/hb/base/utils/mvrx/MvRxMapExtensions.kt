package com.hb.base.utils.mvrx

/**
 * Returns a new immutable map with the provided keys set/updated.
 */
fun <K, V> Map<K, V>.copy(vararg pairs: Pair<K, V>) = HashMap<K, V>(size + pairs.size).apply {
  putAll(this@copy)
  pairs.forEach { put(it.first, it.second) }
}

/**
 * Returns a new map with the provided keys removed.
 */
fun <K, V> Map<K, V>.delete(vararg keys: K): Map<K, V> {
  // This should be slightly more efficient than Map.filterKeys because we start with a map of the
  // correct size rather than a growing LinkedHashMap.
  return HashMap<K, V>(size - keys.size).apply {
    this@delete.entries.asSequence()
      .filter { it.key !in keys }
      .forEach { put(it.key, it.value) }
  }
}
