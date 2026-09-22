package barros.augusto.marcelo.roomsqlite.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

import barros.augusto.marcelo.roomsqlite.entities.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert
    suspend fun insert(book: Book): Long

    @Update
    suspend fun update(book: Book): Int

    @Delete
    suspend fun delete(book: Book): Int

    @Query("SELECT * FROM books ORDER BY title")
    fun listAll(): Flow<List<Book>>

    @Query("SELECT * FROM books WHERE id = :id")
    suspend fun findById(id: Int): Book?
}