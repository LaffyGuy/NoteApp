package com.test.noteappmvvm3.repository

import androidx.lifecycle.LiveData
import com.test.noteappmvvm3.db.NoteDataBase
import com.test.noteappmvvm3.note.Note

class NoteRealisation(val db: NoteDataBase): NoteRepository {

    override fun getAllNotes(): LiveData<List<Note>> {
      return db.getDao().getAllNotes()
    }

    override suspend fun addNote(note: Note) {
        db.getDao().addNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        db.getDao().deleteNote(note)
    }

    override suspend fun deleteAllNotes() {
        db.getDao().deleteAllNotes()
    }

    override suspend fun updateNote(note: Note) {
        db.getDao().updateNote(note)
    }

    override fun favorites(): LiveData<List<Note>> {
        return db.getDao().favorites()
    }
}