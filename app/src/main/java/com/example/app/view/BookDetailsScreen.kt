package com.example.app.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.app.R
import com.example.app.components.BookDetailCard
import com.example.app.components.TopBar
import com.example.app.navigation.MainActions
import com.example.app.utils.DetailViewState
import com.example.app.utils.ViewState
import com.example.app.viewmodel.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BookDetailsScreen(viewModel: MainViewModel, actions: MainActions) {


    Scaffold(topBar = {
        TopBar(title = stringResource(id = R.string.text_bookDetails), action = actions)
    }) {

        BookDetails(viewModel = viewModel)
    }

}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BookDetails(viewModel: MainViewModel) {
    when (val result = viewModel.bookDetails.value) {
        DetailViewState.Loading -> Text(text = "Loading")
        is DetailViewState.Error -> Text(text = "Error found: ${result.exception}")
        is DetailViewState.Success -> {
            val book = result.data

            LazyColumn {
                // Book Details Card
                item {
                    BookDetailCard(book.title, book.authors, book.thumbnailUrl, book.categories)
                }

                // Description
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = stringResource(id = R.string.text_bookDetails),
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = book.longDescription,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Justify,
                        color = Color.Black.copy(0.7F),
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )
                }
            }

        }
        DetailViewState.Empty -> Text("No results found!")
    }
}
