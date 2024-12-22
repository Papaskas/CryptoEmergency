package com.papaska.data.repositories.remote

import com.papaska.data.infrastructure.remote.network.KtorHttpClient
import com.papaska.data.old.data.network.httpClient
import com.papaska.domain.entity.config.ServerConfiguration
import com.papaska.domain.http.ApiResponse
import com.papaska.domain.http.DomainHttpHeaders
import com.papaska.domain.http.DomainHttpMethod
import com.papaska.domain.http.DomainHttpParams
import com.papaska.domain.http.DomainUrlProtocol
import com.papaska.domain.repositories.local.TokenRepository
import com.papaska.domain.repositories.remote.NetworkRepository
import kotlinx.serialization.KSerializer

class NetworkRepositoryImpl(
    private val tokenRepository: TokenRepository,
) : NetworkRepository {

    override suspend operator fun <SuccessResponse, ErrorResponse> invoke(
        serverConfiguration: ServerConfiguration,
        errorResponse: KSerializer<ErrorResponse>,
        successResponse: KSerializer<SuccessResponse>,
        method: DomainHttpMethod,
        path: String,
        protocol: DomainUrlProtocol,
        host: String,
        port: Int,
        body: Any?,
        params: DomainHttpParams,
        headers: DomainHttpHeaders,
        onDownload: (bytesSentTotal: Long, contentLength: Long?) -> Unit,
        onUpload: (bytesSentTotal: Long, contentLength: Long?) -> Unit
    ): ApiResponse<out SuccessResponse, out ErrorResponse> {

        return KtorHttpClient(
            client = httpClient,
            tokenRepository = tokenRepository,
        ).invoke(
            errorResponse = errorResponse,
            successResponse = successResponse,
            host = host,
            path = path,
            port = port,
            method = method,
            protocol = protocol,
            body = body,
            params = params,
            headers = headers,
            onDownload = onDownload,
            onUpload = onUpload
        )
    }
}
