package com.example.mystore.features.updatetransaction.dao

import androidx.room.Dao
import androidx.room.Update
import com.example.mystore.features.registertransaction.room.entities.TransactionEntity

@Dao
interface UpdateTransactionDao {
    @Update
    fun updateTransaction(transaction: TransactionEntity)
}