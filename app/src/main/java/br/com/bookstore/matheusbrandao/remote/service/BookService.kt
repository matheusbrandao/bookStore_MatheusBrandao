package br.com.bookstore.matheusbrandao.remote.service

import br.com.bookstore.matheusbrandao.model.BookHead
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    @GET("v1/volumes")
    suspend fun fetchBookList(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int,
        @Query("startIndex") startIndex: Int,
    ): BookHead
}