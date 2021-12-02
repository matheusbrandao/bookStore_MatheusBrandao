package br.com.bookstore.matheusbrandao.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.bookstore.matheusbrandao.model.BookItem
import br.com.bookstore.matheusbrandao.model.Result
import br.com.bookstore.matheusbrandao.repository.BookRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class BookListViewModel(
    private val repository: BookRepository
) : ViewModel() {

    private val _bookList = MutableLiveData<Result<List<BookItem>>>()
    val bookList: LiveData<Result<List<BookItem>>> get() = _bookList

    fun fetchBooks() {
        viewModelScope.launch {
            repository.fetchBooksFromServer(QUERY_DEFAULT, MAX_RESULTS_DEFAULT, START_INDEX_DEFAULT)
                .onStart {
                    _bookList.postValue(Result.Loading)
                }
                .catch { throwable ->
                    _bookList.postValue(Result.Error(throwable))
                }
                .collect { result ->
                    _bookList.postValue(Result.Success(result))
                }
        }
    }

    companion object {
        private const val QUERY_DEFAULT = "ios"
        private const val MAX_RESULTS_DEFAULT = 20
        private const val START_INDEX_DEFAULT = 0
    }
}