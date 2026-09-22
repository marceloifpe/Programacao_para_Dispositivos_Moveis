package barros.augusto.marcelo.roomsqlite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import barros.augusto.marcelo.roomsqlite.databinding.BookItemBinding
import barros.augusto.marcelo.roomsqlite.entities.Book

class BookAdapter(
    private val onItemClick: (Book) -> Unit,
    private val onDeleteClick: (Book) -> Unit
) : ListAdapter<Book, BookAdapter.BookViewHolder>(BookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val currentBook = getItem(position)
        holder.bind(currentBook)
    }

    inner class BookViewHolder(private val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.tvTitle.text = book.title
            binding.tvAuthor.text = book.author
            binding.tvPublisher.text = book.publisher // A nova editora aqui!
            binding.tvYear.text = book.year.toString()

            binding.root.setOnClickListener { onItemClick(book) }
            binding.btnDelete.setOnClickListener { onDeleteClick(book) }
        }
    }

    // A mágica do DiffUtil acontece aqui: ele ensina a lista a comparar o que é igual e o que mudou
    class BookDiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            // Itens são os mesmos se o ID for igual (é o mesmo livro no banco)
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            // O conteúdo é o mesmo se todas as informações baterem
            return oldItem == newItem
        }
    }
}