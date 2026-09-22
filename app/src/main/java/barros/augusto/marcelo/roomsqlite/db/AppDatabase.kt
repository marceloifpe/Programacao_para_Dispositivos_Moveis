package barros.augusto.marcelo.roomsqlite.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import barros.augusto.marcelo.roomsqlite.dao.BookDao
import barros.augusto.marcelo.roomsqlite.entities.Book

@Database(entities = [Book::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // Se a instância já existir, retorna ela. Se não, cria uma nova.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "books.db"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}