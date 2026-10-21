package com.example.chucksolitario.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Base de datos Room singleton para administrar los favoritos.
 */
@Database(entities = [FavoritoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoritoDao(): FavoritoDao

    companion object {
        @Volatile
        private var instancia: AppDatabase? = null

        // Singleton: una sola instancia de la base de datos en toda la app.
        fun obtener(context: Context): AppDatabase =
            instancia ?: synchronized(this) {
                instancia ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database.db"
                ).build().also { instancia = it }
            }
    }
}
