package com.project.roomdbkotlin.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class TasksViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = NoteRepository(app)
    private val allTaskss = repository.getAllNotes()

    fun insert(Tasks: Note) {
        repository.insert(Tasks)
    }

    fun update(Tasks: Note) {
        repository.update(Tasks)
    }

    fun delete(Tasks: Note) {
        repository.delete(Tasks)
    }

    fun deleteAllTasks() {
        repository.deleteAllNotes()
    }

    fun getAllTasks(): LiveData<List<Note>> {
        return allTaskss
    }


}