package com.example.mystore.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mystore.TransactionType
import java.util.Date

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val productId: Long, // Chave estrangeira referenciando o id do produto
    val transactionType: TransactionType,
    val transactionDate: Date,
    val quantity: Int,
    val transactionAmount: Double,
)
