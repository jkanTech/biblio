package com.jkantech.biblioispt.ui.fragments.books

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class booksViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Livres"
    }
    val text: LiveData<String> = _text
}