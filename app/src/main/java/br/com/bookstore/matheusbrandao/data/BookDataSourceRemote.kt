package br.com.bookstore.matheusbrandao.data

import br.com.bookstore.matheusbrandao.model.BookHead
import br.com.bookstore.matheusbrandao.remote.service.BookService

class BookDataSourceRemote(
    private val service: BookService
) {

    suspend fun fetchBooks(query: String, maxResults: Int, startIndex: Int): BookHead {
        return service.fetchBookList(query, maxResults, startIndex)
    }
}