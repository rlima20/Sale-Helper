package com.example.mystore.room.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mystore.TransactionType
import com.example.mystore.model.Product

@Entity(tableName = "transaction")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val transactionId: Int = 0,
    @ColumnInfo(name = "transaction_type") val transactionType: TransactionType = TransactionType.SALE,
    @ColumnInfo(name = "unit_value") val unitValue: Double = 0.0,
    @ColumnInfo(name = "transaction_date") val transactionDate: String = "",
    @ColumnInfo(name = "transaction_quantity") val quantity: Int = 0,
    @ColumnInfo(name = "transaction_amount") val transactionAmount: Double = 0.0,
    @Embedded val product: Product = Product(),
)
