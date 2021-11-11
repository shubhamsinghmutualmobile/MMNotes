package com.mutualmobile.mmnotes.domain.usecases.notes

import com.mutualmobile.mmnotes.domain.models.Note
import com.mutualmobile.mmnotes.domain.repositories.NoteRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class InsertNoteUseCase : KoinComponent {
    private val noteRepository: NoteRepository by inject()
    operator fun invoke(note: Note) = noteRepository.insertNote(note = note)
}
