package com.mutualmobile.mmnotes.domain.usecases.notes

import com.mutualmobile.mmnotes.domain.repositories.NoteRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetNoteByIdUseCase : KoinComponent {
    private val noteRepository: NoteRepository by inject()
    operator fun invoke(noteId: Int) = noteRepository.getNoteById(id = noteId)
}
