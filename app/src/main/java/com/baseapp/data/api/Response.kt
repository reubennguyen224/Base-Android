package com.baseapp.data.api

class Response<out T> internal constructor(
    val code: Int,
    private val value: Any?
) {
    val isSuccess: Boolean get() = true
    fun getOrNull(): T? {
        return if (isSuccess) value as? T else null
    }

    companion object {
        fun <T> success(value: T): Response<T> {
            return Response(200, value)
        }

        fun <T> failure(code: Int, value: Any?): Response<T> {
            return Response(code, value)
        }
    }
}

inline fun <T> Response<T>.onFailure(block: (code: Int, value: Any?) -> Unit): Response<T> {
    if (!isSuccess) {
        block(code, null)
    }
    return this
}

inline fun <T> Response<T>.onSuccess(block: (value: T) -> Unit): Response<T> {
    if (isSuccess) {
        block(getOrNull() as T)
    }
    return this
}