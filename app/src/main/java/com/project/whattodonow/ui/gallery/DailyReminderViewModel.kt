package com.project.whattodonow.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DailyReminderViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Daily Fragment"
    }
    val text: LiveData<String> = _text
}