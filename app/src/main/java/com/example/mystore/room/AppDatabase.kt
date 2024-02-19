package com.example.mystore.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mystore.room.entities.ProductEntity
import com.example.mystore.room.entities.TransactionEntity
import com.example.mystore.room.dao.ProductDao
import com.example.mystore.room.dao.TransactionDao

/**
 * The Room database for this app
 */

private const val DATA_BASE_NAME = "mystore_database"

@Database(
    entities = [ProductEntity::class, TransactionEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun transactionDao(): TransactionDao

    companion object {

        private lateinit var db: AppDatabase

        fun getInstance(context: Context): AppDatabase {
            if (::db.isInitialized) return db

            db = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DATA_BASE_NAME,
            ).build()

            return db
        }
    }
}
