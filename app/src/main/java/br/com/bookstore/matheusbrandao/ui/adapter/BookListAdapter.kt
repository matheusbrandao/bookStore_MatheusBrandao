package br.com.bookstore.matheusbrandao.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.bookstore.matheusbrandao.databinding.ItemBookListBinding
import br.com.bookstore.matheusbrandao.model.BookItem
import com.bumptech.glide.Glide

class BookListAdapter(
    private var list: List<BookItem> = emptyList()
) : RecyclerView.Adapter<BookListAdapter.BookItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        val binding = ItemBookListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return BookItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    fun updateList(newList: List<BookItem>) {
        list = newList
        notifyDataSetChanged()
    }

    class BookItemViewHolder(
        val binding: ItemBookListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BookItem) {
            with(binding) {
                Glide.with(imageViewBook.context)
                    .load(
                        item.volumeInfo.imageLinks.thumbnail
                    )
                    .into(imageViewBook)

                textViewBookTitle.text = item.volumeInfo.title
                textViewBookSubtitle.text = item.volumeInfo.subtitle
            }
        }
    }
}