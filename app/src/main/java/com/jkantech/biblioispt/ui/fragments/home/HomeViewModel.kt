package com.jkantech.biblioispt.ui.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {


    private val _text = MutableLiveData<String>().apply {
        value = "Acceuil"
    }
    val text: LiveData<String> = _text
}