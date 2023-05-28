package learn.bookservice.repository

import learn.bookservice.entity.CommentEntity
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface CommentRepository: ReactiveCrudRepository<CommentEntity, Long> {
    fun findAllByBookId(bookId: String): Flux<CommentEntity>

    @Modifying
    @Query("update commment set text = :text where id = :id")
    fun updateComment(text: String, id: Long): Mono<Int>
}