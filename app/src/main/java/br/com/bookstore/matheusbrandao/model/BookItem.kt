package br.com.bookstore.matheusbrandao.model

class BookItem(
    var volumeInfo: VolumeInfo
) {

    data class VolumeInfo(
        var title: String,
        var subtitle: String,
        var imageLinks: ImageLinks
    )

    data class ImageLinks(
        var thumbnail: String
    )
}