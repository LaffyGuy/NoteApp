package com.test.noteappmvvm3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.noteappmvvm3.note.Note

@Database(entities = [Note::class], version = 7)
abstract class NoteDataBase: RoomDatabase() {

    abstract fun getDao(): NoteDao

    companion object{

        @Volatile
        private var INSTANCE: NoteDataBase? = null
        fun getDb(context: Context): NoteDataBase {

            val tempInstance = INSTANCE
            if (tempInstance != null) return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDataBase::class.java,
                    "note.db"
                ).fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}