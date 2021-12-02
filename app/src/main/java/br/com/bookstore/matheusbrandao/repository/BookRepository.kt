package br.com.bookstore.matheusbrandao.repository

import br.com.bookstore.matheusbrandao.data.BookDataSourceRemote
import br.com.bookstore.matheusbrandao.model.BookItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BookRepository(
    private val dataSourceRemote: BookDataSourceRemote,
    private val dispatcher: CoroutineDispatcher
) {

    fun fetchBooksFromServer(
        query: String,
        maxResults: Int,
        startIndex: Int
    ): Flow<List<BookItem>> = flow {
        val books = dataSourceRemote.fetchBooks(query, maxResults, startIndex)
        emit(books.items)
    }.flowOn(dispatcher)
}