package com.project.roomdbkotlin.db

import android.app.Application
import androidx.lifecycle.LiveData


class TaskRepository(application: Application) {

    private var taskDao: TaskDao
    private var allNotes: LiveData<List<Task>>

    private val database = TaskDatabase.getInstance(application)

    init {
        taskDao = database.noteDao()
        allNotes = taskDao.getAllNotes()
    }

    fun insert(task: Task) {
//        Single.just(noteDao.insert(note))
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe()
//        subscribeOnBackground {
            taskDao.insert(task)
//        }
    }

    fun update(task: Task) {
//        subscribeOnBackground {
            taskDao.update(task)
//        }
    }

    fun delete(task: Task) {
//        subscribeOnBackground {
            taskDao.delete(task)
//        }
    }

    fun deleteAllNotes() {
//        subscribeOnBackground {
            taskDao.deleteAllNotes()
//        }
    }

    fun getAllNotes(): LiveData<List<Task>> {
        return allNotes
    }

}