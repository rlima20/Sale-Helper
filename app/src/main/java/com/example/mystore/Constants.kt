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
    NONE,
}

enum class TransactionType {
    PURCHASE,
    SALE,
}

enum class States {
    SUCCESS,
    LOADING,
    ERROR,
}

enum class Type {
    CURRENCY_ONLY,
    CURRENCY_PURCHASE,
    CURRENCY_DEBIT_ONLY,
    CURRENCY_TRANSACTION_DETAIL,
    QUANTITY,
    QUANTITY_OOS,
    QUANTITY_TRANSACTION_DETAIL,
    DATE,
    STRING,
    STRING_ONLY,
}

enum class Section {
    RESUME,
    PRODUCTS,
    TRANSACTIONS,
    SALE,
    PURCHASE,
    REGISTER,
    NONE,
}
