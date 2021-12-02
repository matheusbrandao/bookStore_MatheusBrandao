package br.com.bookstore.matheusbrandao.di

import br.com.bookstore.matheusbrandao.remote.interceptor.AuthInterceptor
import br.com.bookstore.matheusbrandao.remote.service.BookService
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://www.googleapis.com/books/"

val serviceModule = module {
    factory { AuthInterceptor() }
    factory { provideOkHttpCliente(get()) }
    factory { provideBookApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()

fun provideOkHttpCliente(authInterceptor: AuthInterceptor): OkHttpClient =
    OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()

fun provideBookApi(retrofit: Retrofit): BookService =
    retrofit.create(BookService::class.java)