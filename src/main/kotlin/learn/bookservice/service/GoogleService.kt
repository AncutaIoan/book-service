package learn.bookservice.service

import learn.bookservice.dto.books.BooksResponse
import learn.bookservice.webclient.GoogleApiClient
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class GoogleService(private val googleApiClient: GoogleApiClient) {
    fun getBooks(keyword: String): Mono<BooksResponse> = googleApiClient.getBooks(keyword)
}