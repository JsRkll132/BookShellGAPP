package com.example.bookshelfapp.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    @SerialName("id")
    val id: String,
    @SerialName("volumeInfo")
    val volumeInfo: VolumeInfo
)