package com.papaska.domain.useCases.remote

interface HttpParams {
    fun get(key: String): String?
    fun getAll(): Map<String, String>
}

/**
 * Пустая реализация [HttpParams].
 */
object EmptyHttpParams : HttpParams {
    override fun get(key: String): String? = null
    override fun getAll(): Map<String, String> = emptyMap()
}
