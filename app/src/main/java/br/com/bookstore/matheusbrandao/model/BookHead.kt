package br.com.bookstore.matheusbrandao.model

data class BookHead(
    var kind: String,
    var totalItems: Int,
    var items: List<BookItem>
)