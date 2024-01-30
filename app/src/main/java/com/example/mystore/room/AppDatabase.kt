package com.example.mystore.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mystore.model.entities.ProductEntity
import com.example.mystore.model.entities.TransactionEntity
import com.example.mystore.room.dao.ProductDao
import com.example.mystore.room.dao.TransactionDao

/**
 * The Room database for this app
 */
@Database(
    entities = [ProductEntity::class, TransactionEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun transactionDao(): TransactionDao
}
