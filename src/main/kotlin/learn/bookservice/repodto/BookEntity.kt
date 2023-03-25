package learn.bookservice.repodto

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("book")
data class BookEntity (
    @Id
    var id: Long? =null,
    @Column("self_link")
    var selfLink: String? = null,
    @Column("sale_info")
    var saleInfo: String? = null,
    @Column("volume_info")
    var volumeInfo: String? = null
)