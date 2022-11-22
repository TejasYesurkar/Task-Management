package com.project.roomdbkotlin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun noteDao(): TaskDao

    companion object {
        private var instance: TaskDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): TaskDatabase {
            if(instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, TaskDatabase::class.java,
                    "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()

            return instance!!

        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                populateDatabase(instance!!)
            }
        }

        private fun populateDatabase(db: TaskDatabase) {
            val noteDao = db.noteDao()
//            subscribeOnBackground {
                noteDao.insert(Task("title 1", "",1))
                noteDao.insert(Task("title 1", "",2))
                noteDao.insert(Task("title 1", "",3))

//            }
        }
    }
}