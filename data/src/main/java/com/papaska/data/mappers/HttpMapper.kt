package com.papaska.data.mappers

import com.papaska.domain.entity.http.DomainHttpHeaders
import com.papaska.domain.entity.http.DomainHttpMethod
import com.papaska.domain.entity.http.DomainHttpParams
import com.papaska.domain.entity.http.DomainHttpStatusCode
import com.papaska.domain.entity.http.DomainUrlProtocol
import io.ktor.http.Headers as KtorHttpHeaders
import io.ktor.http.HttpMethod as KtorHttpMethod
import io.ktor.http.HttpStatusCode as KtorHttpStatusCode
import io.ktor.http.URLProtocol as KtorURLProtocol
import io.ktor.util.StringValues

internal object HttpMapper {
    /**
     * Преобразует [DomainHttpMethod] из доменного слоя в [KtorHttpMethod].
     */
    internal fun DomainHttpMethod.toKtorHttpMethod(): KtorHttpMethod {
        return when (this) {
            DomainHttpMethod.GET -> KtorHttpMethod.Get
            DomainHttpMethod.POST -> KtorHttpMethod.Post
            DomainHttpMethod.PUT -> KtorHttpMethod.Put
            DomainHttpMethod.DELETE -> KtorHttpMethod.Delete
            DomainHttpMethod.PATCH -> KtorHttpMethod.Patch
            DomainHttpMethod.HEAD -> KtorHttpMethod.Head
            DomainHttpMethod.OPTIONS -> KtorHttpMethod.Options
        }
    }

    /**
     * Преобразует из [KtorHttpStatusCode] в [DomainHttpStatusCode].
     */
    internal fun KtorHttpStatusCode.toDomainHttpStatus(): DomainHttpStatusCode {
        return DomainHttpStatusCode(
            value = value,
            description = description,
        )
    }

    /**
     * Преобразует [KtorHttpHeaders] в [DomainHttpHeaders].
     */
    internal fun KtorHttpHeaders.toDomainHttpHeaders(): Map<DomainHttpHeaders, List<String>> {
        return this.entries().associate { (key, values) ->
            DomainHttpHeaders.valueOf(key.uppercase().replace('-', '_')) to values
        }
    }

    /**
     * Преобразует [DomainUrlProtocol] из доменного слоя в [KtorURLProtocol].
     */
    internal fun DomainUrlProtocol.toKtorUrlProtocol(): KtorURLProtocol {
        return when (this) {
            DomainUrlProtocol.HTTP -> KtorURLProtocol.HTTP
            DomainUrlProtocol.HTTPS -> KtorURLProtocol.HTTPS
        }
    }

    /**
     * Преобразует [DomainHttpParams] из доменного слоя в [StringValues].
     */
    internal fun DomainHttpParams.toKtorStringValues(): StringValues {
        return StringValues.build {
            this@toKtorStringValues.entries.forEach { (key, value) ->
                append(key, value)
            }
        }
    }
}