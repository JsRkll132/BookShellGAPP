package com.example.bookshelfapp.network

import com.example.bookshelfapp.model.Book
import retrofit2.http.GET

interface BookApiService {
    @GET("volumes?q=incategories+Fiction")
    suspend fun getBooks(): Book
}