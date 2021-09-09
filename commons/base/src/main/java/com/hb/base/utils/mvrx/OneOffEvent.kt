package com.hb.base.utils.mvrx

/**
 * Use this to avoid re-show Toast, SnackBar on configuration change.
 */
data class OneOffEvent<T>(private val value: T, private val id: Int = 0) {

  var handled: Boolean = false
    private set

  fun handleIfNotYet(block: (T) -> Unit) {
    if (!handled) {
      handled = true
      block(value)
    }
  }

  fun reduce(newValue: T, newHandled: Boolean = false) = copy(value = newValue, id = id + 1).also {
    it.handled = newHandled
  }

  companion object {
    private val HANDLED = OneOffEvent(Unit).apply {
      handled = true
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> handled(): OneOffEvent<T> = HANDLED as OneOffEvent<T>
  }
}
