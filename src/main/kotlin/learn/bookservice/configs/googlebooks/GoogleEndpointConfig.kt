package learn.bookservice.configs.googlebooks

import learn.bookservice.configs.general.EndpointConfig
import learn.bookservice.configs.general.SupportedAuthMode
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "endpoint.google-books")
data class GoogleEndpointConfig(
    override val url: String,
    override val user: String?,
    override val password: String? = null,
    override val poolMaxSize: Int = 50,
    override val connectionTimeoutSec: Int = 10,
    override val readTimeoutSec: Int = 10,
    override val idleTimeoutSec: Int = 10,
    override val authMode: SupportedAuthMode = SupportedAuthMode.NONE
) : EndpointConfig