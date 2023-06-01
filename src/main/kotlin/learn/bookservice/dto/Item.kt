package learn.bookservice.dto

data class Item(
    val id: String,
    val selfLink: String,
    val saleInfo: SaleInfo,
    val volumeInfo: VolumeInfo
)