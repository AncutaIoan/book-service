package learn.bookservice.webclient

import kotlinx.coroutines.reactive.awaitFirstOrNull
import learn.bookservice.configs.googlebooks.GOOGLE_WEB_CLIENT
import learn.bookservice.dto.books.BooksResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.function.Predicate

@Component
class GoogleApiClient(
    @Qualifier(GOOGLE_WEB_CLIENT) private val googleWebClient: WebClient,
    @Value("\${endpoint.google-books.apiKey}") private val apiKey: String
) {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    fun getBooks(keyword: String): Mono<BooksResponse> {
        val path = "/volumes?q=$keyword&filter=free-ebooks&key=$apiKey"
        return googleWebClient.get()
            .uri(path)
            .retrieve()
            .onStatus(Predicate.isEqual(HttpStatus.INTERNAL_SERVER_ERROR)) { Mono.error(RuntimeException("Encountered an error [${it.statusCode()}]")) }
            .bodyToMono(BooksResponse::class.java)
            .doOnError {
                log.error("Error during books call with message: ${it.message}")
            }
    }
}