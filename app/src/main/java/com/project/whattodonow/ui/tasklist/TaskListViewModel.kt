package com.project.whattodonow.ui.tasklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Task List Fragment"
    }
    val text: LiveData<String> = _text
}