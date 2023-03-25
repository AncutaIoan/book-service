package learn.bookservice.configs.general

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.client.reactive.ClientHttpConnector
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

object WebClientBuilder {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)
    fun build(endpointConfig: EndpointConfig) =
        WebClient.builder()
            .baseUrl(endpointConfig.url)
            .defaultCookie(HttpHeaders.AUTHORIZATION)
            .filter(logRequest())
            .clientConnector(connectorOf)
            .build()

    private fun logRequest(): ExchangeFilterFunction = ExchangeFilterFunction.ofRequestProcessor {
        log.info("Request {}:{}", it.method(), it.url());Mono.just(it)
    }
}

private val connectorOf: ClientHttpConnector
    get() = ReactorClientHttpConnector(httpClient)
private val httpClient
    get() = reactor.netty.http.client.HttpClient.create()