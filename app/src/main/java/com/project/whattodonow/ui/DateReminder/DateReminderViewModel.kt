package com.project.whattodonow.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DateReminderViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Date Fragment"
    }
    val text: LiveData<String> = _text
}