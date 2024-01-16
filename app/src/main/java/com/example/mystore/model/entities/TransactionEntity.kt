package com.example.mystore.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mystore.TransactionType
import java.util.Date

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val productId: Long, // Chave estrangeira referenciando o id do produto
    val transactionType: TransactionType = TransactionType.SALE,
    val unitValue: Double = 0.0,
    val transactionDate: Date = Date(),
    val quantity: Int = 0,
    val transactionAmount: Double = 0.0,
)
