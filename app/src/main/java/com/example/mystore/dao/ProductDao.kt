package com.example.mystore.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mystore.model.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Insert
    fun insertProduct(product: ProductEntity)

    @Update
    fun updateProduct(product: ProductEntity)

    @Delete
    fun deleteProduct(product: ProductEntity)
}
