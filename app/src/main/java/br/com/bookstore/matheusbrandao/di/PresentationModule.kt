package br.com.bookstore.matheusbrandao.di

import br.com.bookstore.matheusbrandao.viewModel.BookListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val presentationModule = module {

    viewModel {
        BookListViewModel(get())
    }
}