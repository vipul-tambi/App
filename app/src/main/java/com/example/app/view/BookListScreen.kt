package com.example.app.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.app.R
import com.example.app.components.ItemBookList
import com.example.app.components.TextInputField
import com.example.app.model.BookItem
import com.example.app.navigation.MainActions
import com.example.app.utils.ViewState
import com.example.app.viewmodel.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BookListScreen(viewModel: MainViewModel,actions:MainActions) {
    when(val result=viewModel.books.value)
    {
        ViewState.Empty -> Text(text = "No results found...")
        is ViewState.Error -> Text(text = "Error found : ${result.exception}")
        ViewState.Loading -> Text(text = "Loading")
        is ViewState.Success -> {
            BookList(result.data, actions)
        }
    }
}


@Composable
fun BookList(bookList: List<BookItem>, actions: MainActions) {
    val search=remember{
        mutableStateOf("")
    }

    val listState = rememberLazyListState()


    LazyColumn(state = listState, contentPadding = PaddingValues(top = 24.dp, bottom = 24.dp),
        modifier = Modifier.background(color=Color.Cyan.copy(0.1f))){
        //title
        item{
            Text(text = "Explore thousands of \nbooks in go", style = MaterialTheme.typography.h5,
            textAlign=TextAlign.Start,
            color= Color.Black,
            maxLines=2,
            modifier=Modifier.padding(start=16.dp,end=16.dp,bottom=24.dp))
        }

        //search
        item{
            TextInputField(label = stringResource(R.string.text_search), value = search.value, onValueChanged = {it->
               search.value=it

            })
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))

        }

        //Search results title
        item{
            Text(text = "Famous Book",
                style=MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(start=16.dp))

        }

        items(bookList.filter { it.title.contains(search.value, ignoreCase = true) }){book->

            ItemBookList(book.title,book.authors.toString(),book.thumbnailUrl,book.categories,onItemClick={
                actions.gotoBookDetails.invoke(book.isbn)
            })
        }




    }
}
