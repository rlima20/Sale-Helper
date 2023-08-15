package com.example.mystore

val screenList =
    listOf(
        R.string.my_store_home.toStringResource(),
        R.string.my_store_register_product.toStringResource(),
        R.string.my_store_register_transaction.toStringResource(),
        R.string.my_store_consolidated_position.toStringResource(),
    )

enum class Screens {
    HOME,
    REGISTER_PRODUCT,
    REGISTER_TRANSACTION,
    CONSOLIDATED_POSITION,
    NONE
}

enum class States {
    SUCCESS,
    LOADING,
    ERROR,
}

enum class Type {
    PURCHASE_CURRENCY,
    CURRENCY,
    QUANTITY,
    DATE,
    STRING,
}

enum class Section {
    RESUME,
    PRODUCTS,
    TRANSACTIONS,
    SALE,
    PURCHASE,
    REGISTER,
}
