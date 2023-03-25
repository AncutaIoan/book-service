package learn.bookservice.repository

import learn.bookservice.repodto.BookEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface BookRepository: R2dbcRepository<BookEntity, Int> {
    fun getBookById(id: Int): Mono<BookEntity>
}