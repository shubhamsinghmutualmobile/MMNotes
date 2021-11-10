package com.mutualmobile.mmnotes.android.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Surface
import com.mutualmobile.mmnotes.android.theme.BaseComposeTheme
import com.mutualmobile.mmnotes.android.ui.components.NotesGridView
import com.mutualmobile.mmnotes.data.datasources.local.MMNotesDatabase
import com.mutualmobile.mmnotes.data.sources.local.DatabaseDriverFactory
import com.mutualmobile.mmnotes.data.sources.local.repositories.NoteRepositoryImpl
import com.mutualmobile.mmnotes.domain.models.Note
import com.mutualmobile.mmnotes.domain.repositories.NoteRepository
import com.mutualmobile.mmnotes.domain.usecases.notes.DeleteAllNotesUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.GetAllNotesUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.InsertNoteUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.SearchNotesUseCase
import com.mutualmobile.mmnotes.viewmodels.NotesViewModel

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {

        val sqlDriver = DatabaseDriverFactory(context = application).createDriver()
        val database = MMNotesDatabase(driver = sqlDriver)
        val noteRepository: NoteRepository = NoteRepositoryImpl(database)
        val notesViewModel = NotesViewModel(
            deleteAllNotesUseCase = DeleteAllNotesUseCase(noteRepository = noteRepository),
            getAllNotesUseCase = GetAllNotesUseCase(noteRepository = noteRepository),
            searchNotesUseCase = SearchNotesUseCase(noteRepository = noteRepository),
            insertNoteUseCase = InsertNoteUseCase(noteRepository = noteRepository)
        )

        super.onCreate(savedInstanceState)

        setContent {
            BaseComposeTheme {
                Surface {
                    NotesGridView(
                        notes = listOf(
                            Note(1, "Test", "TestBody\nTestBody\nTestBody", 123456),
                            Note(1, "Test", "TestBody\nTestBody\nTestBody", 123456),
                            Note(1, "Test", "TestBody\nTestBody\nTestBody", 123456),
                            Note(1, "Test", "TestBody\nTestBody\nTestBody", 123456),
                            Note(1, "Test", "TestBody\nTestBody\nTestBody", 123456),
                            Note(1, "Test", "TestBody\nTestBody\nTestBody", 123456),
                            Note(1, "Test", "TestBody\nTestBody\nTestBody", 123456),
                            Note(1, "Test", "TestBody\nTestBody\nTestBody", 123456),
                            Note(1, "Test", "TestBody\nTestBody\nTestBody", 123456),
                            Note(1, "Test", "TestBody\nTestBody\nTestBody", 123456),
                            Note(1, "Test", "TestBody\nTestBody\nTestBody", 123456),
                            Note(1, "Test", "TestBody\nTestBody\nTestBody", 123456),
                            Note(1, "Test", "TestBody\nTestBody\nTestBody", 123456),
                            Note(1, "Test", "TestBody\nTestBody\nTestBody", 123456),
                            Note(1, "Test", "TestBody\nTestBody\nTestBody", 123456),
                            Note(1, "Test", "TestBody\nTestBody\nTestBody", 123456),
                        )
                    )
                }
            }
        }
    }
}
