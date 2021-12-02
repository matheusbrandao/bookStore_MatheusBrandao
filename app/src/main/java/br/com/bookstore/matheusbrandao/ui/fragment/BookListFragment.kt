package br.com.bookstore.matheusbrandao.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import br.com.bookstore.matheusbrandao.R
import br.com.bookstore.matheusbrandao.databinding.FragmentBookListBinding
import br.com.bookstore.matheusbrandao.model.BookItem
import br.com.bookstore.matheusbrandao.model.Result
import br.com.bookstore.matheusbrandao.ui.adapter.BookListAdapter
import br.com.bookstore.matheusbrandao.viewModel.BookListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookListFragment : Fragment() {

    private lateinit var binding: FragmentBookListBinding
    private lateinit var adapter: BookListAdapter
    private val viewModel by viewModel<BookListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_book_list, container, false)
        binding = FragmentBookListBinding.bind(root)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListAdapter()
        setupObservables()
        viewModel.fetchBooks()
    }

    private fun setupObservables() {
        viewModel.bookList.observe(viewLifecycleOwner, { state ->
            when (state) {
                is Result.Error -> {
                    setVisibilityLoading(false)
                    showMessageError()
                }
                Result.Loading -> setVisibilityLoading(true)
                is Result.Success -> {
                    setVisibilityLoading(false)
                    handleWithBooksResult(state.data)
                }
            }
        })
    }

    private fun handleWithBooksResult(books: List<BookItem>) {
        adapter.updateList(books)
    }

    private fun setVisibilityLoading(visible: Boolean) {
        binding.progressBarBook.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun showMessageError() {
        Toast.makeText(context, getString(R.string.msg_error_request), Toast.LENGTH_SHORT).show()
    }

    private fun setupListAdapter() {
        adapter = BookListAdapter()

        with(binding.recyclerViewBookList) {
            adapter = this@BookListFragment.adapter
            layoutManager = GridLayoutManager(
                context,
                COLUMNS_IN_LIST,
                GridLayoutManager.VERTICAL,
                false
            )
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
            addItemDecoration(DividerItemDecoration(context, LinearLayout.HORIZONTAL))
        }
    }

    companion object {
        private const val COLUMNS_IN_LIST = 2
    }
}