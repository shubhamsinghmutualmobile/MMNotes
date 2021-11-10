package com.mutualmobile.mmnotes.domain.usecases.notes

import com.mutualmobile.mmnotes.domain.repositories.NoteRepository

class SearchNotesUseCase constructor(
    private val noteRepository: NoteRepository
) {
    operator fun invoke(title: String) = noteRepository.searchNotesByTitle(title = title)
}
