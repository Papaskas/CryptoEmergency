package com.papaska.domain.useCases.remote.test

import com.papaska.domain.entity.config.ServerConfiguration
import com.papaska.domain.entity.http.DomainHttpHeaders
import com.papaska.domain.entity.http.DomainHttpMethod
import com.papaska.domain.entity.http.DomainHttpParams
import com.papaska.domain.entity.http.DomainUrlProtocol
import com.papaska.domain.repositories.remote.NetworkRepository
import kotlinx.serialization.KSerializer

class TestRequestUseCase(
    private val networkRepository: NetworkRepository,
    private val serverConfiguration: ServerConfiguration,
) {
    suspend operator fun<Success, Error> invoke(
        success: KSerializer<Success>,
        error: KSerializer<Error>,
        method: DomainHttpMethod,
        path: String,
        protocol: DomainUrlProtocol,
        host: String,
        port: Int,
        body: Any? = null,
        params: DomainHttpParams = emptyMap(),
        headers: DomainHttpHeaders = emptyMap(),
        onDownload: (bytesSentTotal: Long, contentLength: Long?) -> Unit = { _, _ -> },
        onUpload: (bytesSentTotal: Long, contentLength: Long?) -> Unit = { _, _ -> },
    ) =
        networkRepository.invoke(
            successResponse = success,
            errorResponse = error,
            serverConfiguration = serverConfiguration,
            method = method,
            path = path,
            protocol = protocol,
            host = host,
            port = port,
            body = body,
            params = params,
            headers = headers,
            onDownload = onDownload,
            onUpload = onUpload,
        )
}