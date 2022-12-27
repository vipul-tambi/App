package com.example.app.utils

import com.example.app.model.BookItem

sealed class ViewState{
    object Empty:ViewState()
    object Loading:ViewState()
    data class Success(val data:List<BookItem>):ViewState()
    data class Error(val exception:Throwable):ViewState()
}
