package learn.bookservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class BooksResponse(
    val kind: String,
    val totalItems: Long,
    val items: List<Item>
)