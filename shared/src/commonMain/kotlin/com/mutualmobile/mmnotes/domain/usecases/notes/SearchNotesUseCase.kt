package com.mutualmobile.mmnotes.domain.usecases.notes

import com.mutualmobile.mmnotes.domain.repositories.NoteRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchNotesUseCase : KoinComponent {
    private val noteRepository: NoteRepository by inject()
    operator fun invoke(title: String) = noteRepository.searchNotesByTitle(title = title)
}
