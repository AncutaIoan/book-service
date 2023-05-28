package learn.bookservice.controller

import learn.bookservice.dto.BooksResponse
import learn.bookservice.service.GoogleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class BookController(private val googleService: GoogleService) {
    @GetMapping("/getBooks")
    fun getBooks(@RequestParam keyword: String = "programming"): Mono<BooksResponse>? {
        return googleService.getBooks(keyword)
    }
}