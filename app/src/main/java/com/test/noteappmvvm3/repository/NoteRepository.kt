package com.test.noteappmvvm3.repository

import androidx.lifecycle.LiveData
import com.test.noteappmvvm3.note.Note

interface NoteRepository {

    fun getAllNotes(): LiveData<List<Note>>

    suspend fun addNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun deleteAllNotes()

    suspend fun updateNote(note: Note)

    fun favorites(): LiveData<List<Note>>
}