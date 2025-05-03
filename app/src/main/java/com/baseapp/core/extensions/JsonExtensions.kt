package com.baseapp.core.extensions

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val json = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
}

inline fun <reified T> T.encodeToString() = json.encodeToString(this)

inline fun <reified T> String.decodeFromString() = json.decodeFromString<T>(this)