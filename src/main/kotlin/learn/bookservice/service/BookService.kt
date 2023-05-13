package learn.bookservice.service

import learn.bookservice.repodto.BookEntity
import learn.bookservice.repository.BookRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class BookService(val bookRepository: BookRepository) {
    companion object {
        val log = LoggerFactory.getLogger(BookService::class.java)
    }

    fun getBookFromDB(bookId: Int): Mono<BookEntity> {
        return bookRepository.getBookById(bookId)
            .doOnError { log.error("Book with id [$bookId] could not be retrieved from db due to {$it}") }
    }

    @Transactional
    fun saveBooks(bookEntities: List<BookEntity>): Mono<List<BookEntity>> {
        return bookRepository.saveAll(bookEntities).collectList()
    }

    @Transactional
    fun saveBook(book: BookEntity): Mono<BookEntity> {
        return bookRepository.save(book)
    }
}


