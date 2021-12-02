package br.com.bookstore.matheusbrandao.di

import br.com.bookstore.matheusbrandao.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module.module

val repositoryModule = module {

    factory {
        BookRepository(get(), Dispatchers.IO)
    }
}