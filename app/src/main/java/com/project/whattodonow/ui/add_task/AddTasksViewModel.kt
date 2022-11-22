package com.project.whattodonow.ui.add_task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddTasksViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Add Tasks Fragment"
    }
    val text: LiveData<String> = _text
}