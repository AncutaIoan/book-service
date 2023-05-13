package learn.bookservice.service

import learn.bookservice.repodto.CommentEntity
import learn.bookservice.repository.CommentRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CommentService(private val commentRepository: CommentRepository) {
    fun getCommentsByBookId(bookId: String): Flux<CommentEntity> {
        return commentRepository.findAllByBookId(bookId)
    }
    fun addComment(comment: CommentEntity): Mono<CommentEntity> {
        return commentRepository.save(comment)
    }
    fun editComment(id: Long, text: String): Mono<Int> {
        return commentRepository.updateComment(text, id)
    }
}