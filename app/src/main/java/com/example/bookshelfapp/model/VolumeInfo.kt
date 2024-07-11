package com.example.bookshelfapp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VolumeInfo(
    @SerialName("authors")
    val authors: List<String>,
    @SerialName("description")
    val description: String,
    @SerialName("imageLinks")
    val imageLinks: ImageLinks,
    @SerialName("publishedDate")
    val publishedDate: String,
    @SerialName("subtitle")
    val subtitle: String,
    @SerialName("title")
    val title: String
)