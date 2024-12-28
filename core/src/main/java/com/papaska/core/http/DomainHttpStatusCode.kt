package com.papaska.core.http

/**
 * Представляет HTTP-статус ответа.
 *
 * @property code [Int] Код статуса HTTP.
 * @property description [String] Описание статуса.
 */
data class DomainHttpStatusCode(val value: Int, val description: String) : Comparable<DomainHttpStatusCode> {
    override fun toString(): String = "$value $description"

    override fun equals(other: Any?): Boolean = other is DomainHttpStatusCode && other.value == value

    override fun hashCode(): Int = value.hashCode()

    /**
     * @return копию [DomainHttpStatusCode] с новым [description].
     */
    fun description(value: String): DomainHttpStatusCode = copy(description = value)

    override fun compareTo(other: DomainHttpStatusCode): Int = value - other.value

    companion object {
        /**
         * Кастомный статус HTTP для ошибок сериализации.
         */
        val SerializationException = DomainHttpStatusCode(-1, "Serialization exception")

        /**
         * Кастомный статус HTTP для ошибок ввода-вывода.
         */
        val IOException = DomainHttpStatusCode(-900, "IO exception")

        /**
         * Кастомный статус HTTP для ошибок, связанных с неизвестным хостом.
         */
        val UnknownHostException = DomainHttpStatusCode(-1000, "Unknown host exception")

        val Continue = DomainHttpStatusCode(100, "Continue")
        val SwitchingProtocols = DomainHttpStatusCode(101, "Switching Protocols")
        val Processing = DomainHttpStatusCode(102, "Processing")

        val OK = DomainHttpStatusCode(200, "OK")
        val Created = DomainHttpStatusCode(201, "Created")
        val Accepted = DomainHttpStatusCode(202, "Accepted")

        val NonAuthoritativeInformation = DomainHttpStatusCode(203, "Non-Authoritative Information")

        val NoContent = DomainHttpStatusCode(204, "No Content")
        val ResetContent = DomainHttpStatusCode(205, "Reset Content")
        val PartialContent = DomainHttpStatusCode(206, "Partial Content")
        val MultiStatus = DomainHttpStatusCode(207, "Multi-Status")

        val MultipleChoices = DomainHttpStatusCode(300, "Multiple Choices")
        val MovedPermanently = DomainHttpStatusCode(301, "Moved Permanently")
        val Found = DomainHttpStatusCode(302, "Found")
        val SeeOther = DomainHttpStatusCode(303, "See Other")
        val NotModified = DomainHttpStatusCode(304, "Not Modified")
        val UseProxy = DomainHttpStatusCode(305, "Use Proxy")
        val SwitchProxy = DomainHttpStatusCode(306, "Switch Proxy")
        val TemporaryRedirect = DomainHttpStatusCode(307, "Temporary Redirect")
        val PermanentRedirect = DomainHttpStatusCode(308, "Permanent Redirect")

        val BadRequest = DomainHttpStatusCode(400, "Bad Request")
        val Unauthorized = DomainHttpStatusCode(401, "Unauthorized")
        val PaymentRequired = DomainHttpStatusCode(402, "Payment Required")
        val Forbidden = DomainHttpStatusCode(403, "Forbidden")
        val NotFound = DomainHttpStatusCode(404, "Not Found")
        val MethodNotAllowed = DomainHttpStatusCode(405, "Method Not Allowed")
        val NotAcceptable = DomainHttpStatusCode(406, "Not Acceptable")

        val ProxyAuthenticationRequired = DomainHttpStatusCode(407, "Proxy Authentication Required")

        val RequestTimeout = DomainHttpStatusCode(408, "Request Timeout")
        val Conflict = DomainHttpStatusCode(409, "Conflict")
        val Gone = DomainHttpStatusCode(410, "Gone")
        val LengthRequired = DomainHttpStatusCode(411, "Length Required")
        val PreconditionFailed = DomainHttpStatusCode(412, "Precondition Failed")
        val PayloadTooLarge = DomainHttpStatusCode(413, "Payload Too Large")
        val RequestURITooLong = DomainHttpStatusCode(414, "Request-URI Too Long")

        val UnsupportedMediaType = DomainHttpStatusCode(415, "Unsupported Media Type")

        val RequestedRangeNotSatisfiable = DomainHttpStatusCode(416, "Requested Range Not Satisfiable")

        val ExpectationFailed = DomainHttpStatusCode(417, "Expectation Failed")
        val UnprocessableEntity = DomainHttpStatusCode(422, "Unprocessable Entity")
        val Locked = DomainHttpStatusCode(423, "Locked")
        val FailedDependency = DomainHttpStatusCode(424, "Failed Dependency")
        val TooEarly = DomainHttpStatusCode(425, "Too Early")
        val UpgradeRequired = DomainHttpStatusCode(426, "Upgrade Required")
        val TooManyRequests = DomainHttpStatusCode(429, "Too Many Requests")

        val RequestHeaderFieldTooLarge =
            DomainHttpStatusCode(431, "Request Header Fields Too Large")

        val InternalServerError = DomainHttpStatusCode(500, "Internal Server Error")
        val NotImplemented = DomainHttpStatusCode(501, "Not Implemented")
        val BadGateway = DomainHttpStatusCode(502, "Bad Gateway")
        val ServiceUnavailable = DomainHttpStatusCode(503, "Service Unavailable")
        val GatewayTimeout = DomainHttpStatusCode(504, "Gateway Timeout")

        val VersionNotSupported = DomainHttpStatusCode(505, "HTTP Version Not Supported")

        val VariantAlsoNegotiates = DomainHttpStatusCode(506, "Variant Also Negotiates")
        val InsufficientStorage = DomainHttpStatusCode(507, "Insufficient Storage")

        /**
         * Все http статусы
         */
        val allStatusCodes = allStatusCodes()

        private val statusCodesMap = allStatusCodes.associateBy { it.value }

        /**
         * Создает экземпляр [DomainHttpStatusCode] из [value].
         */
        fun fromValue(value: Int): DomainHttpStatusCode {
            return statusCodesMap[value] ?: DomainHttpStatusCode(value, "Unknown Status Code")
        }
    }
}

internal fun allStatusCodes(): List<DomainHttpStatusCode> = listOf(
    DomainHttpStatusCode.Continue,
    DomainHttpStatusCode.SwitchingProtocols,
    DomainHttpStatusCode.Processing,
    DomainHttpStatusCode.OK,
    DomainHttpStatusCode.Created,
    DomainHttpStatusCode.Accepted,
    DomainHttpStatusCode.NonAuthoritativeInformation,
    DomainHttpStatusCode.NoContent,
    DomainHttpStatusCode.ResetContent,
    DomainHttpStatusCode.PartialContent,
    DomainHttpStatusCode.MultiStatus,
    DomainHttpStatusCode.MultipleChoices,
    DomainHttpStatusCode.MovedPermanently,
    DomainHttpStatusCode.Found,
    DomainHttpStatusCode.SeeOther,
    DomainHttpStatusCode.NotModified,
    DomainHttpStatusCode.UseProxy,
    DomainHttpStatusCode.SwitchProxy,
    DomainHttpStatusCode.TemporaryRedirect,
    DomainHttpStatusCode.PermanentRedirect,
    DomainHttpStatusCode.BadRequest,
    DomainHttpStatusCode.Unauthorized,
    DomainHttpStatusCode.PaymentRequired,
    DomainHttpStatusCode.Forbidden,
    DomainHttpStatusCode.NotFound,
    DomainHttpStatusCode.MethodNotAllowed,
    DomainHttpStatusCode.NotAcceptable,
    DomainHttpStatusCode.ProxyAuthenticationRequired,
    DomainHttpStatusCode.RequestTimeout,
    DomainHttpStatusCode.Conflict,
    DomainHttpStatusCode.Gone,
    DomainHttpStatusCode.LengthRequired,
    DomainHttpStatusCode.PreconditionFailed,
    DomainHttpStatusCode.PayloadTooLarge,
    DomainHttpStatusCode.RequestURITooLong,
    DomainHttpStatusCode.UnsupportedMediaType,
    DomainHttpStatusCode.RequestedRangeNotSatisfiable,
    DomainHttpStatusCode.ExpectationFailed,
    DomainHttpStatusCode.UnprocessableEntity,
    DomainHttpStatusCode.Locked,
    DomainHttpStatusCode.FailedDependency,
    DomainHttpStatusCode.TooEarly,
    DomainHttpStatusCode.UpgradeRequired,
    DomainHttpStatusCode.TooManyRequests,
    DomainHttpStatusCode.RequestHeaderFieldTooLarge,
    DomainHttpStatusCode.InternalServerError,
    DomainHttpStatusCode.NotImplemented,
    DomainHttpStatusCode.BadGateway,
    DomainHttpStatusCode.ServiceUnavailable,
    DomainHttpStatusCode.GatewayTimeout,
    DomainHttpStatusCode.VersionNotSupported,
    DomainHttpStatusCode.VariantAlsoNegotiates,
    DomainHttpStatusCode.InsufficientStorage
)

/**
 * Checks if a given status code is a success code according to HTTP standards.
 *
 * Codes from 200 to 299 are considered to be successful.
 */
fun DomainHttpStatusCode.isSuccess(): Boolean = value in (200 until 300)