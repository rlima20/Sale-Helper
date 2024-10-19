package com.example.mystore.features.registertransaction.room.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mystore.enums.TransactionType
import com.example.mystore.features.registerproduct.model.Product

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
