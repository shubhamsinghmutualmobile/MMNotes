package com.mutualmobile.mmnotes.data.sources.local.repositories

import com.mutualmobile.mmnotes.data.datasources.local.MMNotesDatabase
import com.mutualmobile.mmnotes.data.sources.mappers.toNote
import com.mutualmobile.mmnotes.data.sources.models.NoteEntity
import com.mutualmobile.mmnotes.domain.models.Note
import com.mutualmobile.mmnotes.domain.repositories.NoteRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NoteRepositoryImpl : NoteRepository, KoinComponent {

    private val database: MMNotesDatabase by inject()

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

    override fun getNoteById(id: Int): Note? {
        return database.mMNotesDatabaseQueries.getNoteById(id = id.toLong()).executeAsList().firstOrNull()?.let { dbNoteEntity ->
            NoteEntity(
                id = dbNoteEntity.id.toInt(),
                title = dbNoteEntity.title,
                body = dbNoteEntity.body,
                dateCreated = dbNoteEntity.dateCreated
            ).toNote()
        }
    }

    override fun updateNote(note: Note) {
        note.id?.let { nnNoteId ->
            database.mMNotesDatabaseQueries.updateNote(
                title = note.title,
                body = note.body,
                dateCreated = note.dateCreated,
                id = nnNoteId.toLong()
            )
        }
    }
}
