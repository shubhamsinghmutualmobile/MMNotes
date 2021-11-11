package com.mutualmobile.mmnotes.data.di

import com.mutualmobile.mmnotes.data.datasources.local.MMNotesDatabase
import com.mutualmobile.mmnotes.data.sources.local.repositories.NoteRepositoryImpl
import com.mutualmobile.mmnotes.domain.repositories.NoteRepository
import com.mutualmobile.mmnotes.domain.usecases.notes.DeleteAllNotesUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.GetAllNotesUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.GetNoteByIdUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.InsertNoteUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.SearchNotesUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.UpdateNoteUseCase
import com.mutualmobile.mmnotes.viewmodels.NotesViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules()
}

fun initKoin() = initKoin {}

val commonModule = module {
    single { MMNotesDatabase(get()) }
    single<NoteRepository> { NoteRepositoryImpl() }
    single { NotesViewModel(get(), get(), get(), get(), get(), get()) }
    single { DeleteAllNotesUseCase() }
    single { GetAllNotesUseCase() }
    single { InsertNoteUseCase() }
    single { SearchNotesUseCase() }
    single { GetNoteByIdUseCase() }
    single { UpdateNoteUseCase() }
}
