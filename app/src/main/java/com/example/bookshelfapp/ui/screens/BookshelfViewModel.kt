package com.example.bookshelfapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelfapp.BookshelfApplication
import com.example.bookshelfapp.data.BookshelfRepository
import com.example.bookshelfapp.model.Book
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface BookshelfUiState {
    data class Success(val books: Book) : BookshelfUiState
    object Error : BookshelfUiState
    object Loading : BookshelfUiState
}

class BookshelfViewModel(
    private val bookshelfRepository: BookshelfRepository
) : ViewModel() {
    var bookshelfUiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)
        private set

    init {
        getAllBooks()
    }

    fun getAllBooks() {
        viewModelScope.launch {
            bookshelfUiState = try {
                BookshelfUiState.Success(bookshelfRepository.getAllBooks())
            } catch (e: IOException) {
                BookshelfUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfApplication)
                val bookshelfRepository = application.container.bookshelfRepository
                BookshelfViewModel(bookshelfRepository = bookshelfRepository)
            }
        }
    }
}