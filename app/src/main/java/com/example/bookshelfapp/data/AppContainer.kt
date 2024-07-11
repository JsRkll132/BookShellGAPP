package com.example.bookshelfapp.data

import com.example.bookshelfapp.network.BookApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val bookshelfRepository: BookshelfRepository
}

class DefaultAppContainer : AppContainer {

    private val baseUrl =
        "https://www.googleapis.com/books/v1/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: BookApiService by lazy {
        retrofit.create(BookApiService::class.java)
    }

    override val bookshelfRepository: BookshelfRepository by lazy {
        NetworkBookshelfRepository(retrofitService)
    }
}