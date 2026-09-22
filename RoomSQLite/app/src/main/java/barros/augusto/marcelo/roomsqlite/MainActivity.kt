package barros.augusto.marcelo.roomsqlite

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import barros.augusto.marcelo.roomsqlite.adapter.BookAdapter
import barros.augusto.marcelo.roomsqlite.dao.BookDao
import barros.augusto.marcelo.roomsqlite.databinding.ActivityMainBinding
import barros.augusto.marcelo.roomsqlite.db.AppDatabase
import barros.augusto.marcelo.roomsqlite.entities.Book
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var database: AppDatabase
    private lateinit var bookDao: BookDao
    private lateinit var adapter: BookAdapter

    private var selectedBook: Book? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.getDatabase(this)
        bookDao = database.bookDao()

        configureRecyclerView()
        configureButtons()
        observeBooks()
    }

    private fun configureRecyclerView() {
        adapter = BookAdapter(
            onItemClick = { book -> selectBook(book) },
            onDeleteClick = { book -> deleteBook(book) }
        )
        binding.rvBooks.layoutManager = LinearLayoutManager(this)
        binding.rvBooks.adapter = adapter
    }

    private fun configureButtons() {
        binding.btnSave.setOnClickListener {
            insertBook()
        }

        binding.btnUpdate.setOnClickListener {
            updateBook()
        }
    }

    private fun insertBook() {
        val title = binding.etTitle.text.toString()
        val author = binding.etAuthor.text.toString()
        val publisher = binding.etPublisher.text.toString()
        val year = binding.etYear.text.toString()

        if (title.isBlank() || author.isBlank() || publisher.isBlank() || year.isBlank()) {
            Toast.makeText(this, getString(R.string.msg_fill_all_fields), Toast.LENGTH_SHORT).show()
            return
        }

        val book = Book(title = title, author = author, publisher = publisher, year = year.toInt())

        lifecycleScope.launch {
            bookDao.insert(book)
            clearForm()
            Toast.makeText(this@MainActivity, getString(R.string.msg_book_inserted), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateBook() {
        val book = selectedBook

        if (book == null) {
            Toast.makeText(this, getString(R.string.msg_select_book), Toast.LENGTH_SHORT).show()
            return
        }

        val title = binding.etTitle.text.toString()
        val author = binding.etAuthor.text.toString()
        val publisher = binding.etPublisher.text.toString()
        val year = binding.etYear.text.toString()

        if (title.isBlank() || author.isBlank() || publisher.isBlank() || year.isBlank()) {
            Toast.makeText(this, getString(R.string.msg_fill_all_fields), Toast.LENGTH_SHORT).show()
            return
        }

        val updatedBook = book.copy(title = title, author = author, publisher = publisher, year = year.toInt())

        lifecycleScope.launch {
            bookDao.update(updatedBook)
            selectedBook = null
            clearForm()
            Toast.makeText(this@MainActivity, getString(R.string.msg_book_updated), Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearForm() {
        binding.etTitle.text.clear()
        binding.etAuthor.text.clear()
        binding.etPublisher.text.clear()
        binding.etYear.text.clear()

        selectedBook = null
    }

    private fun deleteBook(book: Book) {
        lifecycleScope.launch {
            bookDao.delete(book)

            if (selectedBook?.id == book.id) {
                selectedBook = null
                clearForm()
            }

            Toast.makeText(this@MainActivity, getString(R.string.msg_book_deleted), Toast.LENGTH_SHORT).show()
        }
    }

    private fun selectBook(book: Book) {
        selectedBook = book

        binding.etTitle.setText(book.title)
        binding.etAuthor.setText(book.author)
        binding.etPublisher.setText(book.publisher)
        binding.etYear.setText(book.year.toString())

        Toast.makeText(this, getString(R.string.msg_book_selected), Toast.LENGTH_SHORT).show()
    }

    private fun observeBooks() {
        lifecycleScope.launch {
            bookDao.listAll().collect { books -> adapter.submitList(books) }
        }
    }
}