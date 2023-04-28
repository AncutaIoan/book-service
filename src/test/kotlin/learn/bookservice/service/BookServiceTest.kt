package learn.bookservice.service

import learn.bookservice.repodto.BookEntity
import learn.bookservice.repository.BookRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

internal class BookServiceTest {
    private val bookRepository: BookRepository = Mockito.mock(BookRepository::class.java)
    private val bookService = BookService(bookRepository)

    @Test
    fun getBookFromDB_whenBookExist_returnBook() {
        val book = BookEntity(1, "someLink", "saleInfo", "volumeInfo")

        `when`(bookRepository.getBookById(1)).thenReturn(Mono.just(book))

        StepVerifier.create(bookService.getBookFromDB(1))
            .expectNextMatches { it == book }
            .verifyComplete()
    }

    @Test
    fun getBookFromDB_whenBookDoesNotExist_returnBook() {
        `when`(bookRepository.getBookById(1)).thenReturn(Mono.empty())
        StepVerifier.create(bookService.getBookFromDB(1))
            .verifyComplete()
    }

    @Test
    fun saveBook_whenSuccessfulSave_returnBookEntity() {
        val book = BookEntity(1, "someLink", "saleInfo", "volumeInfo")

        `when`(bookRepository.save(book)).thenReturn(Mono.just(book))

        StepVerifier.create(bookService.saveBook(book))
            .expectNext(book)
            .verifyComplete()
    }

    @Test
    fun saveBooks_whenSuccessfulSave_returnBookEntities() {
        val book = BookEntity(1, "someLink", "saleInfo", "volumeInfo")
        val books = listOf(book)

        `when`(bookRepository.saveAll(books)).thenReturn(Flux.just(book))

        StepVerifier.create(bookService.saveBooks(books))
            .expectNextCount(1)
            .verifyComplete()
    }
}