package com.papaska.domain.useCases.remote

/**
 * Представляет заголовки HTTP-ответа.
 *
 * @property headers Карта заголовков, где ключ — имя заголовка, а значение — список значений.
 */
data class HttpHeaders(
    val headers: Map<String, List<String>>,
) {
    /**
     * Получает значение заголовка по имени.
     *
     * @param name Имя заголовка.
     * @return Значение заголовка или null, если заголовок не найден.
     */
    fun get(name: String): String? = headers[name]?.firstOrNull()
}
