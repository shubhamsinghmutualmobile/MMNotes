package com.mutualmobile.mmnotes.android.ui.screens.landing_screen

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mutualmobile.mmnotes.android.R
import com.mutualmobile.mmnotes.android.ui.screens.landing_screen.components.NotesBottomAppBar
import com.mutualmobile.mmnotes.android.ui.screens.landing_screen.components.NotesGridView
import com.mutualmobile.mmnotes.android.ui.screens.landing_screen.components.TopSearchBar
import com.mutualmobile.mmnotes.viewmodels.NotesViewModel
import com.ramcosta.composedestinations.NoteDetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.icerock.moko.mvvm.livedata.asFlow
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalFoundationApi::class)
@Destination(start = true)
@Composable
fun LandingPage(
    navigator: DestinationsNavigator,
    notesViewModel: NotesViewModel = getViewModel()
) {
    val notes by notesViewModel.listOfNotesLiveData.asFlow().collectAsState(initial = listOf())
    notesViewModel.getAllNotes()
    Surface {
        val fabShape = RoundedCornerShape(16.dp)
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navigator.navigate(NoteDetailsScreenDestination(isNewNote = true)) },
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
            NotesGridView(
                notes = notes,
                navigator = navigator
            )
        }
    }
}
