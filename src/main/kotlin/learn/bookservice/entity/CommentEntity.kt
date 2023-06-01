package learn.bookservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("comment")
data class CommentEntity(
        @Id
        var id: Int? = null,
        @Column("text")
        val text: String,
        @Column("rating")
        val rating: Int,
        @Column("added_on")
        val addedOn: LocalDateTime,
        @Column("user_id")
        val userId: String,
        @Column("book_id")
        val bookId: String
)