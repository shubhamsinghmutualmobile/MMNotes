package com.mutualmobile.mmnotes.data.sources.local.repositories

import com.mutualmobile.mmnotes.data.datasources.local.MMNotesDatabase
import com.mutualmobile.mmnotes.data.sources.mappers.toNote
import com.mutualmobile.mmnotes.data.sources.models.NoteEntity
import com.mutualmobile.mmnotes.domain.models.Note
import com.mutualmobile.mmnotes.domain.repositories.NoteRepository

class NoteRepositoryImpl constructor(
    private val database: MMNotesDatabase
) : NoteRepository {

    override fun insertNote(note: Note) {
        database.mMNotesDatabaseQueries.insertNote(
            id = note.id?.toLong(),
            title = note.title,
            body = note.body,
            dateCreated = note.dateCreated
        )
    }

    override fun searchNotesByTitle(title: String): List<Note> {
        return database
            .mMNotesDatabaseQueries
            .searchNotesByTitle(title = title) { id, dbNoteTitle, body, dateCreated ->
                NoteEntity(
                    id = id.toInt(),
                    title = dbNoteTitle,
                    body = body,
                    dateCreated = dateCreated
                ).toNote()
            }.executeAsList()
    }

    override fun getAllNotes(): List<Note> {
        return database.mMNotesDatabaseQueries.getAllNotes { id, title, body, dateCreated ->
            NoteEntity(
                id = id.toInt(),
                title = title,
                body = body,
                dateCreated = dateCreated
            ).toNote()
        }.executeAsList()
    }

    override fun deleteAllNotes() {
        database.mMNotesDatabaseQueries.removeAllNotes()
    }
}
