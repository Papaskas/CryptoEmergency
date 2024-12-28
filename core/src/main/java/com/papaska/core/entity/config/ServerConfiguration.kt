package com.papaska.core.entity.config

import com.papaska.core.http.DomainUrlProtocol

/**
 * Представляет конфигурацию сервера, необходимую для выполнения сетевых запросов.
 *
 * Этот класс содержит информацию о протоколе, хосте и порте, которые используются для подключения к серверу.
 *
 * @property protocol Протокол (например, http, https)
 * @property host Имя хоста или IP-адрес сервера
 * @property port Номер порта сервера
 */
data class ServerConfiguration (
    val protocol: DomainUrlProtocol,
    val host: String,
    val port: Int,
)
