package learn.bookservice.dto.books

data class VolumeInfo(
    val title: String,
    val subtitle: String?,
    val authors: List<String> = emptyList(),
    val publishedDate: String?,
    val pageCount: Int?,
    val categories: List<String> = emptyList(),
    val maturityRating: String?,
    val language: String?
)