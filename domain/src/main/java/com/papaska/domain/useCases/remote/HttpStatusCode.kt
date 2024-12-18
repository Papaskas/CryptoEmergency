package com.papaska.domain.useCases.remote

/**
 * Представляет HTTP-статус ответа.
 *
 * @property code [Int] Код статуса HTTP.
 * @property description [String] Описание статуса.
 */
data class HttpStatusCode(val value: Int, val description: String) : Comparable<HttpStatusCode> {
    override fun toString(): String = "$value $description"

    override fun equals(other: Any?): Boolean = other is HttpStatusCode && other.value == value

    override fun hashCode(): Int = value.hashCode()

    /**
     * @return копию [HttpStatusCode] с новым [description].
     */
    fun description(value: String): HttpStatusCode = copy(description = value)

    override fun compareTo(other: HttpStatusCode): Int = value - other.value

    companion object {
        /**
         * Кастомный статус HTTP для ошибок сериализации.
         */
        val SerializationException = HttpStatusCode(-1, "Serialization exception")

        /**
         * Кастомный статус HTTP для ошибок ввода-вывода.
         */
        val IOException = HttpStatusCode(-900, "IO exception")

        /**
         * Кастомный статус HTTP для ошибок, связанных с неизвестным хостом.
         */
        val UnknownHostException = HttpStatusCode(-1000, "Unknown host exception")

        val Continue = HttpStatusCode(100, "Continue")
        val SwitchingProtocols = HttpStatusCode(101, "Switching Protocols")
        val Processing = HttpStatusCode(102, "Processing")

        val OK = HttpStatusCode(200, "OK")
        val Created = HttpStatusCode(201, "Created")
        val Accepted = HttpStatusCode(202, "Accepted")

        val NonAuthoritativeInformation = HttpStatusCode(203, "Non-Authoritative Information")

        val NoContent = HttpStatusCode(204, "No Content")
        val ResetContent = HttpStatusCode(205, "Reset Content")
        val PartialContent = HttpStatusCode(206, "Partial Content")
        val MultiStatus = HttpStatusCode(207, "Multi-Status")

        val MultipleChoices = HttpStatusCode(300, "Multiple Choices")
        val MovedPermanently = HttpStatusCode(301, "Moved Permanently")
        val Found = HttpStatusCode(302, "Found")
        val SeeOther = HttpStatusCode(303, "See Other")
        val NotModified = HttpStatusCode(304, "Not Modified")
        val UseProxy = HttpStatusCode(305, "Use Proxy")
        val SwitchProxy = HttpStatusCode(306, "Switch Proxy")
        val TemporaryRedirect = HttpStatusCode(307, "Temporary Redirect")
        val PermanentRedirect = HttpStatusCode(308, "Permanent Redirect")

        val BadRequest = HttpStatusCode(400, "Bad Request")
        val Unauthorized = HttpStatusCode(401, "Unauthorized")
        val PaymentRequired = HttpStatusCode(402, "Payment Required")
        val Forbidden = HttpStatusCode(403, "Forbidden")
        val NotFound = HttpStatusCode(404, "Not Found")
        val MethodNotAllowed = HttpStatusCode(405, "Method Not Allowed")
        val NotAcceptable = HttpStatusCode(406, "Not Acceptable")

        val ProxyAuthenticationRequired = HttpStatusCode(407, "Proxy Authentication Required")

        val RequestTimeout = HttpStatusCode(408, "Request Timeout")
        val Conflict = HttpStatusCode(409, "Conflict")
        val Gone = HttpStatusCode(410, "Gone")
        val LengthRequired = HttpStatusCode(411, "Length Required")
        val PreconditionFailed = HttpStatusCode(412, "Precondition Failed")
        val PayloadTooLarge = HttpStatusCode(413, "Payload Too Large")
        val RequestURITooLong = HttpStatusCode(414, "Request-URI Too Long")

        val UnsupportedMediaType = HttpStatusCode(415, "Unsupported Media Type")

        val RequestedRangeNotSatisfiable = HttpStatusCode(416, "Requested Range Not Satisfiable")

        val ExpectationFailed = HttpStatusCode(417, "Expectation Failed")
        val UnprocessableEntity = HttpStatusCode(422, "Unprocessable Entity")
        val Locked = HttpStatusCode(423, "Locked")
        val FailedDependency = HttpStatusCode(424, "Failed Dependency")
        val TooEarly = HttpStatusCode(425, "Too Early")
        val UpgradeRequired = HttpStatusCode(426, "Upgrade Required")
        val TooManyRequests = HttpStatusCode(429, "Too Many Requests")

        val RequestHeaderFieldTooLarge =
            HttpStatusCode(431, "Request Header Fields Too Large")

        val InternalServerError = HttpStatusCode(500, "Internal Server Error")
        val NotImplemented = HttpStatusCode(501, "Not Implemented")
        val BadGateway = HttpStatusCode(502, "Bad Gateway")
        val ServiceUnavailable = HttpStatusCode(503, "Service Unavailable")
        val GatewayTimeout = HttpStatusCode(504, "Gateway Timeout")

        val VersionNotSupported = HttpStatusCode(505, "HTTP Version Not Supported")

        val VariantAlsoNegotiates = HttpStatusCode(506, "Variant Also Negotiates")
        val InsufficientStorage = HttpStatusCode(507, "Insufficient Storage")

        /**
         * Все http статусы
         */
        val allStatusCodes = allStatusCodes()

        private val statusCodesMap = allStatusCodes.associateBy { it.value }

        /**
         * Создает экземпляр [HttpStatusCode] из [value].
         */
        fun fromValue(value: Int): HttpStatusCode {
            return statusCodesMap[value] ?: HttpStatusCode(value, "Unknown Status Code")
        }
    }
}

internal fun allStatusCodes(): List<HttpStatusCode> = listOf(
    HttpStatusCode.Continue,
    HttpStatusCode.SwitchingProtocols,
    HttpStatusCode.Processing,
    HttpStatusCode.OK,
    HttpStatusCode.Created,
    HttpStatusCode.Accepted,
    HttpStatusCode.NonAuthoritativeInformation,
    HttpStatusCode.NoContent,
    HttpStatusCode.ResetContent,
    HttpStatusCode.PartialContent,
    HttpStatusCode.MultiStatus,
    HttpStatusCode.MultipleChoices,
    HttpStatusCode.MovedPermanently,
    HttpStatusCode.Found,
    HttpStatusCode.SeeOther,
    HttpStatusCode.NotModified,
    HttpStatusCode.UseProxy,
    HttpStatusCode.SwitchProxy,
    HttpStatusCode.TemporaryRedirect,
    HttpStatusCode.PermanentRedirect,
    HttpStatusCode.BadRequest,
    HttpStatusCode.Unauthorized,
    HttpStatusCode.PaymentRequired,
    HttpStatusCode.Forbidden,
    HttpStatusCode.NotFound,
    HttpStatusCode.MethodNotAllowed,
    HttpStatusCode.NotAcceptable,
    HttpStatusCode.ProxyAuthenticationRequired,
    HttpStatusCode.RequestTimeout,
    HttpStatusCode.Conflict,
    HttpStatusCode.Gone,
    HttpStatusCode.LengthRequired,
    HttpStatusCode.PreconditionFailed,
    HttpStatusCode.PayloadTooLarge,
    HttpStatusCode.RequestURITooLong,
    HttpStatusCode.UnsupportedMediaType,
    HttpStatusCode.RequestedRangeNotSatisfiable,
    HttpStatusCode.ExpectationFailed,
    HttpStatusCode.UnprocessableEntity,
    HttpStatusCode.Locked,
    HttpStatusCode.FailedDependency,
    HttpStatusCode.TooEarly,
    HttpStatusCode.UpgradeRequired,
    HttpStatusCode.TooManyRequests,
    HttpStatusCode.RequestHeaderFieldTooLarge,
    HttpStatusCode.InternalServerError,
    HttpStatusCode.NotImplemented,
    HttpStatusCode.BadGateway,
    HttpStatusCode.ServiceUnavailable,
    HttpStatusCode.GatewayTimeout,
    HttpStatusCode.VersionNotSupported,
    HttpStatusCode.VariantAlsoNegotiates,
    HttpStatusCode.InsufficientStorage
)

/**
 * Checks if a given status code is a success code according to HTTP standards.
 *
 * Codes from 200 to 299 are considered to be successful.
 */
fun HttpStatusCode.isSuccess(): Boolean = value in (200 until 300)