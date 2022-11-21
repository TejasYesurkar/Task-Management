package com.project.roomdbkotlin.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class TaskViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = TaskRepository(app)
    private val allNotes = repository.getAllNotes()

    fun insert(task: Task) {
        repository.insert(task)
    }

    fun update(task: Task) {
        repository.update(task)
    }

    fun delete(task: Task) {
        repository.delete(task)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<Task>> {
        return allNotes
    }


}