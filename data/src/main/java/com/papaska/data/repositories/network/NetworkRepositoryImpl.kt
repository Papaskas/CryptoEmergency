package com.papaska.data.repositories.network

import com.papaska.data.infrastructure.remote.apiNetwork.KtorHttpClient
import com.papaska.data.infrastructure.remote.apiNetwork.httpClient
import com.papaska.domain.entity.config.ServerConfiguration
import com.papaska.domain.http.ApiResponse
import com.papaska.domain.entity.http.DomainHttpHeaders
import com.papaska.domain.entity.http.DomainHttpMethod
import com.papaska.domain.entity.http.DomainHttpParams
import com.papaska.domain.entity.http.DomainUrlProtocol
import com.papaska.domain.entity.local.TokenEntity
import com.papaska.domain.repositories.local.storage.LocalStorageRepository
import com.papaska.domain.repositories.remote.NetworkRepository
import kotlinx.serialization.KSerializer

class NetworkRepositoryImpl(
    private val tokenRepository: LocalStorageRepository<TokenEntity>,
) : NetworkRepository {

    /**
     * Suspend function to perform a network request.
     *
     * @param SuccessResponse Type of the successful response object.
     * @param ErrorResponse Type of the error response object.
     * @param serverConfiguration [ServerConfiguration] Configuration of the server to connect to.
     * @param errorResponse [ErrorResponse] Serializer for the error response type.
     * @param successResponse [SuccessResponse] Serializer for the success response type.
     * @param method [DomainHttpMethod] HTTP method for the request (GET, POST, PUT, etc.).
     * @param path [String] Path of the API endpoint.
     * @param protocol [DomainUrlProtocol] (Optional) Protocol for the request
     * (defaults to serverConfiguration.protocol).
     * @param host [String] (Optional) Host of the server (defaults to serverConfiguration.host).
     * @param port [Int] (Optional) Port of the server (defaults to serverConfiguration.port).
     * @param body (Optional) Request body object.
     * @param params [DomainHttpParams] (Optional) Map of query parameters.
     * @param headers [DomainHttpHeaders] (Optional) Map of request headers.
     * @param onDownload [Unit] Callback for download progress updates.
     * @param onUpload [Unit] Callback for upload progress updates.
     *
     * @return [ApiResponse] containing either the success response or the error response.
     * @throws Exception Any exception that may occur during the network request.
     */
    override suspend fun <SuccessResponse, ErrorResponse> invoke(
        method: DomainHttpMethod,
        path: String,
        errorResponse: KSerializer<ErrorResponse>,
        successResponse: KSerializer<SuccessResponse>,
        serverConfiguration: ServerConfiguration,
        protocol: DomainUrlProtocol,
        host: String,
        port: Int,
        body: Any?,
        params: DomainHttpParams,
        headers: Map<DomainHttpHeaders, List<String>>,
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
