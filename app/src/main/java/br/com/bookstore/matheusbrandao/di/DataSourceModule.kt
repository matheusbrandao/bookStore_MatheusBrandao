package br.com.bookstore.matheusbrandao.di

import br.com.bookstore.matheusbrandao.data.BookDataSourceRemote
import org.koin.dsl.module.module

val dataSourceModule = module {

    factory {
        BookDataSourceRemote(get())
    }
}