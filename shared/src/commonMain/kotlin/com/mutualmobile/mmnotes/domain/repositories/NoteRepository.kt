package com.mutualmobile.mmnotes.domain.repositories

import com.mutualmobile.mmnotes.domain.models.Note

interface NoteRepository {
    fun insertNote(note: Note)
    fun searchNotesByTitle(title: String): List<Note>
    fun getAllNotes(): List<Note>
    fun deleteAllNotes()
}
