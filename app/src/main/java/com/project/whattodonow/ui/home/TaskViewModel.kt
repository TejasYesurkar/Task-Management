package com.project.roomdbkotlin.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class TasksViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = TaskRepository(app)
    private val allTaskss = repository.getAllNotes()

    fun insert(Tasks: Task) {
        repository.insert(Tasks)
    }

    fun update(Tasks: Task) {
        repository.update(Tasks)
    }

    fun delete(Tasks: Task) {
        repository.delete(Tasks)
    }

    fun deleteAllTasks() {
        repository.deleteAllNotes()
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return allTaskss
    }


}