package com.mutualmobile.mmnotes.android.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mutualmobile.mmnotes.android.R
import com.mutualmobile.mmnotes.data.datasources.local.MMNotesDatabase
import com.mutualmobile.mmnotes.data.sources.local.DatabaseDriverFactory
import com.mutualmobile.mmnotes.data.sources.local.repositories.NoteRepositoryImpl
import com.mutualmobile.mmnotes.domain.repositories.NoteRepository
import com.mutualmobile.mmnotes.domain.usecases.notes.DeleteAllNotesUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.GetAllNotesUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.InsertNoteUseCase
import com.mutualmobile.mmnotes.domain.usecases.notes.SearchNotesUseCase
import com.mutualmobile.mmnotes.viewmodels.NotesViewModel
import com.ramcosta.composedestinations.NoteDetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.icerock.moko.mvvm.livedata.asFlow

@OptIn(ExperimentalFoundationApi::class)
@Destination(start = true)
@Composable
fun LandingPage(
    navigator: DestinationsNavigator
) {

    val ctx = LocalContext.current

    val sqlDriver by remember {
        mutableStateOf(
            DatabaseDriverFactory(context = ctx).createDriver()
        )
    }
    val database by remember {
        mutableStateOf(
            MMNotesDatabase(driver = sqlDriver)
        )
    }
    val noteRepository: NoteRepository by remember {
        mutableStateOf(
            NoteRepositoryImpl(database)
        )
    }
    val notesViewModel by remember {
        mutableStateOf(
            NotesViewModel(
                deleteAllNotesUseCase = DeleteAllNotesUseCase(noteRepository = noteRepository),
                getAllNotesUseCase = GetAllNotesUseCase(noteRepository = noteRepository),
                searchNotesUseCase = SearchNotesUseCase(noteRepository = noteRepository),
                insertNoteUseCase = InsertNoteUseCase(noteRepository = noteRepository)
            )
        )
    }

    Surface {
        val fabShape = RoundedCornerShape(16.dp)
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navigator.navigate(NoteDetailsScreenDestination) },
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
