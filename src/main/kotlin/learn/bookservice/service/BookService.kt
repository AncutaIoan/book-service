package learn.bookservice.service

import learn.bookservice.repodto.BookEntity
import learn.bookservice.repository.BookRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class BookService(val bookRepository: BookRepository) {
    fun getBookFromDB(): Mono<BookEntity> {
        return bookRepository.getBookById(1)
    }
    @Transactional
    fun saveBooks(bookEntities: List<BookEntity>): Flux<BookEntity> {
        return bookRepository.saveAll(bookEntities)
    }
    @Transactional
    fun save(book: BookEntity): Mono<BookEntity> {
        return bookRepository.save(book)
    }
}


