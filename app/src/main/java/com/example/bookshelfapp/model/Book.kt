package com.example.bookshelfapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    @SerialName("items")
    val items: List<Item>,
    @SerialName("totalItems")
    val totalItems: Int
)