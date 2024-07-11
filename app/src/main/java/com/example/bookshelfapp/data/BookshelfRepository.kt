package com.example.bookshelfapp.data

import com.example.bookshelfapp.model.Book
import com.example.bookshelfapp.network.BookApiService

interface BookshelfRepository {
    suspend fun getAllBooks(): Book
}

class NetworkBookshelfRepository (
    private val bookApiService: BookApiService
): BookshelfRepository {
    override suspend fun getAllBooks(): Book = bookApiService.getBooks()
}
