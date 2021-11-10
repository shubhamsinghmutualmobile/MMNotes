package com.mutualmobile.mmnotes.domain.usecases.notes

import com.mutualmobile.mmnotes.domain.models.Note
import com.mutualmobile.mmnotes.domain.repositories.NoteRepository

class InsertNoteUseCase constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(note: Note) = noteRepository.insertNote(note = note)
}
