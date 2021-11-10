package com.mutualmobile.mmnotes.domain.usecases.notes

import com.mutualmobile.mmnotes.domain.repositories.NoteRepository

class GetAllNotesUseCase constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke() = noteRepository.getAllNotes()
}
