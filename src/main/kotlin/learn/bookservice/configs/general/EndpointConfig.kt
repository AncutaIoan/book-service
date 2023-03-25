package learn.bookservice.configs.general

interface EndpointConfig {
    val url: String
    val user: String?
    val password: String?
    val poolMaxSize: Int
    val connectionTimeoutSec: Int
    val readTimeoutSec: Int
    val idleTimeoutSec: Int
    val authMode: SupportedAuthMode
}

enum class SupportedAuthMode {
    BASIC, BEARER, NONE
}