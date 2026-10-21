package com.example.chucksolitario.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Base de datos Room singleton con migración a la versión 2 para la tabla de caché.
 */
@Database(
    entities = [FavoritoEntity::class, FactCacheEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoritoDao(): FavoritoDao
    abstract fun factCacheDao(): FactCacheDao

    companion object {
        @Volatile
        private var instancia: AppDatabase? = null

        private val MIGRACION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "CREATE TABLE IF NOT EXISTS `fact_cache` (" +
                            "`id` TEXT NOT NULL, " +
                            "`text` TEXT NOT NULL, " +
                            "`source` TEXT NOT NULL, " +
                            "PRIMARY KEY(`id`))"
                )
            }
        }

        fun obtener(context: Context): AppDatabase =
            instancia ?: synchronized(this) {
                instancia ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database.db"
                )
                    .addMigrations(MIGRACION_1_2)
                    .build()
                    .also { instancia = it }
            }
    }
}
