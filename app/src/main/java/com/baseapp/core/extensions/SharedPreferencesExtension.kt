package com.baseapp.core.extensions

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

inline fun <reified T> SharedPreferences.preferences(key: String, initialValue: T) =
    object : ReadWriteProperty<Any, T> {
        override fun getValue(thisRef: Any, property: KProperty<*>): T {
            return getSharedValue(key, initialValue)
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
            setSharedValue(key, value)
        }
    }

inline fun <reified T> SharedPreferences.preferencesFlow(key: String, initialValue: T) =
    ReadOnlyProperty<Any, Flow<T>> { _, _ ->
        callbackFlow {
            val initData = getSharedValue(key, initialValue)
            trySend(initData)
            val listener =
                SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, k ->
                    if (k == key) {
                        trySend(sharedPreferences.getSharedValue(key, initialValue))
                    }
                }
            registerOnSharedPreferenceChangeListener(listener)
            awaitClose {
                unregisterOnSharedPreferenceChangeListener(listener)
            }
        }
    }


inline fun <reified T> SharedPreferences.getSharedValue(key: String, defaultValue: T): T {
    return when (T::class) {
        Boolean::class -> this.getBoolean(key, defaultValue as Boolean) as T
        Float::class -> this.getFloat(key, defaultValue as Float) as T
        Int::class -> this.getInt(key, defaultValue as Int) as T
        Long::class -> this.getLong(key, defaultValue as Long) as T
        String::class -> this.getString(key, defaultValue as String) as T
        Double::class -> Double.fromBits(this.getLong(key, (defaultValue as Double).toRawBits())) as T
        else -> try {
            (this.getString(key, "") ?: "").decodeFromString<T>()
                ?: defaultValue
        } catch (e: Throwable) {
            defaultValue
        }
    }
}

inline fun <reified T> SharedPreferences.setSharedValue(key: String, value: T) {
    edit(true) {
        when (T::class) {
            Boolean::class -> putBoolean(key, value as Boolean)
            Float::class -> putFloat(key, value as Float)
            Int::class -> putInt(key, value as Int)
            Long::class -> putLong(key, value as Long)
            String::class -> putString(key, value as String)
            Double::class -> putLong(key, (value as Double).toRawBits())
            else -> {
                putString(key, value.encodeToString())
            }
        }
    }
}