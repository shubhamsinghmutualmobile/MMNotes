package com.mutualmobile.mmnotes.android.ui.screens.landing_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mutualmobile.mmnotes.domain.models.Note
import com.mutualmobile.mmnotes.viewmodels.NotesViewModel
import com.ramcosta.composedestinations.NoteDetailsScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

@ExperimentalFoundationApi
@Composable
fun NotesGridView(
    notes: List<Note>,
    gridSize: Int = 2,
    padding: Dp = 4.dp,
    navigator: DestinationsNavigator,
    notesViewModel: NotesViewModel = getViewModel()
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = padding)
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(gridSize),
            content = {
                notes.forEach { note ->
                    item {
                        NoteCard(
                            note = note,
                            padding = padding,
                            navigator = navigator,
                            notesViewModel = notesViewModel
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.padding(padding * 11))
                }
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun NoteCard(
    padding: Dp,
    note: Note,
    navigator: DestinationsNavigator,
    notesViewModel: NotesViewModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(padding),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f)
        ),
        shape = RoundedCornerShape(padding * 2)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .combinedClickable(
                    onClick = {
                        navigator.navigate(
                            NoteDetailsScreenDestination(
                                noteId = note.id.toString(),
                                isNewNote = false
                            )
                        )
                    },
                    onLongClick = {
                        note.id?.let { nnId ->
                            notesViewModel.deleteNoteById(nnId)
                        }
                    }
                )
                .padding(padding * 3),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = note.title, style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.padding(padding / 2))
            Text(text = note.body, style = MaterialTheme.typography.body2)
        }
    }
}
