package com.example.mystore.features.registertransaction.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mystore.features.registertransaction.room.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query("SELECT * FROM 'transaction'") // 'transaction' is a reserved word in SQL
    fun getAllTransactions(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM 'transaction' WHERE transactionId = :id")
    fun getTransactionById(id: Int): Flow<TransactionEntity>

    @Insert
    fun insertTransaction(transaction: TransactionEntity)

    @Delete
    fun deleteTransaction(transaction: TransactionEntity)
}
