package com.example.bookshelfapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelfapp.R
import com.example.bookshelfapp.model.Book

@Composable
fun HomeScreen(
    retryAction: () -> Unit,
    bookshelfUiState: BookshelfUiState,
    modifier: Modifier = Modifier
) {
    when (bookshelfUiState) {
        is BookshelfUiState.Loading -> LoadingScreen(
            modifier = modifier.fillMaxSize()
        )
        is BookshelfUiState.Success -> {
            val multipliedBooks = (1..10).flatMap { listOf(bookshelfUiState.books) }
            BooksGridScreen(
                books = multipliedBooks,
                modifier = modifier.fillMaxSize()
            )
        }
        is BookshelfUiState.Error -> ErrorScreen(
            retryAction = retryAction,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun BooksGridScreen(
    books: List<Book>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(16.dp)
    ) {
        itemsIndexed(books) { index, book ->
            BookCard(
                book = book,
                index = index
            )
        }
    }
}

@Composable
fun BookCard(
    book: Book,
    index: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(book.items[index].volumeInfo.imageLinks.smallThumbnail.replace("http", "https"))
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(id = R.string.image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = 72.dp, height = 128.dp)
                    .clip(MaterialTheme.shapes.small)
                    .weight(1f),
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f)
            ) {
                Text(
                    text = book.items[index].volumeInfo.title,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = book.items[index].volumeInfo.authors[0],
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = stringResource(id = R.string.loading)
    )
}

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier= modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.loading_failed),
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = retryAction) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

//@Preview
//@Composable
//fun BooksGridScreen() {
//    BookshelfAppTheme {
//        val mockData = List(10) { Book(listOf(), 1) }
//        BooksGridScreen(mockData[0])
//    }
//}