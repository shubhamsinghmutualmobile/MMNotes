package com.mutualmobile.mmnotes.android.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mutualmobile.mmnotes.android.R
import com.mutualmobile.mmnotes.android.theme.BaseComposeTheme
import com.mutualmobile.mmnotes.android.ui.components.NotesBottomAppBar
import com.mutualmobile.mmnotes.android.ui.components.NotesGridView
import com.mutualmobile.mmnotes.android.ui.components.TopSearchBar
import com.mutualmobile.mmnotes.data.datasources.local.MMNotesDatabase
import com.mutualmobile.mmnotes.data.sources.local.DatabaseDriverFactory
import com.mutualmobile.mmnotes.data.sources.local.repositories.NoteRepositoryImpl
import com.mutualmobile.mmnotes.domain.repositories.NoteRepository
import com.mutualmobile.mmnotes.domain.usecases.notes.DeleteAllNotesUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.GetAllNotesUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.InsertNoteUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.SearchNotesUseCase
import com.mutualmobile.mmnotes.viewmodels.NotesViewModel
import dev.icerock.moko.mvvm.livedata.asFlow

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
                    val fabShape = RoundedCornerShape(16.dp)
                    Scaffold(
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = {},
                                shape = fabShape
                            ) {
                                Image(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = stringResource(R.string.fab_description)
                                )
                            }
                        },
                        isFloatingActionButtonDocked = true,
                        floatingActionButtonPosition = FabPosition.End,
                        bottomBar = { NotesBottomAppBar(fabShape) },
                        topBar = {
                            TopSearchBar()
                        }
                    ) {
                        val notes by notesViewModel.listOfNotesLiveData.asFlow().collectAsState(
                            initial = listOf()
                        )
                        NotesGridView(
                            notes = notes
                        )
                    }
                }
            }
        }
    }
}
