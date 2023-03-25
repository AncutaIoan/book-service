package learn.bookservice.configs.googlebooks

import learn.bookservice.configs.general.WebClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

const val GOOGLE_WEB_CLIENT = "GOOGLE_WEB_CLIENT"

@Configuration
class GoogleConfig {
    @Bean(GOOGLE_WEB_CLIENT)
    fun googleWebClient(config: GoogleEndpointConfig): WebClient {
        return WebClientBuilder.build(config)
    }
}