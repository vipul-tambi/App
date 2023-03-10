package com.example.app.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.model.BookItem
import com.example.app.utils.DetailViewState
import com.example.app.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class MainViewModel:ViewModel() {
    private val _viewState= MutableStateFlow<ViewState>(ViewState.Loading)
    val books = _viewState.asStateFlow()

    private val _detailViewState = MutableStateFlow<DetailViewState>(DetailViewState.Loading)
    val bookDetails = _detailViewState.asStateFlow()

    //Helps to format the json
    val format= Json {
        ignoreUnknownKeys = true
        prettyPrint = true
        isLenient = true
    }
    // get all the data from the Book.json
    fun getAllBooks(context: Context)=viewModelScope.launch {
        try {
            //read the json file
            val myJson=context.assets.open("books.json").bufferedReader().use{
                it.readText()
            }

            //format json
            val bookList=format.decodeFromString<List<BookItem>>(myJson)
            _viewState.value=ViewState.Success(bookList)
        } catch (e:Exception){
            _viewState.value=ViewState.Error(e)
        }
    }


    fun getBookByID(context: Context, isbnNO:String) = viewModelScope.launch {
        try {

            // read JSON File
            val myJson = context.assets.open("books.json").bufferedReader().use {
                it.readText()
            }

            // format JSON
            val bookList = format.decodeFromString<List<BookItem>>(myJson) .filter { it.isbn.contentEquals(isbnNO)}.first()
            _detailViewState.value = DetailViewState.Success(bookList)

        } catch (e: Exception){
            _detailViewState.value = DetailViewState.Error(e)
        }
    }
}
