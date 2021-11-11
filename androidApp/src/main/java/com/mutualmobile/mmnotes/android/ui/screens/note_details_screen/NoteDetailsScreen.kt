package com.mutualmobile.mmnotes.android.ui.screens.note_details_screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mutualmobile.mmnotes.android.ui.screens.note_details_screen.compoenents.BottomActionBar
import com.mutualmobile.mmnotes.android.ui.screens.note_details_screen.compoenents.NoteRow
import com.mutualmobile.mmnotes.android.ui.screens.note_details_screen.compoenents.TitleRow
import com.mutualmobile.mmnotes.android.ui.screens.note_details_screen.compoenents.TopActionBar
import com.mutualmobile.mmnotes.domain.models.Note
import com.mutualmobile.mmnotes.viewmodels.NotesViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel

private const val TAG = "NoteDetailsScreen"

@OptIn(ExperimentalAnimationApi::class)
@Destination
@Composable
fun NoteDetailsScreen(
    navigator: DestinationsNavigator,
    noteId: String? = null,
    notesViewModel: NotesViewModel = getViewModel(),
    isNewNote: Boolean
) {
    var note: Note? = null

    BackHandler(
        enabled = true,
        onBack = {
            onBackPressed(isNewNote, note, notesViewModel, navigator)
        }
    )

    noteId?.let {
        val noteIdInt = it.toInt()
        note = notesViewModel.getNoteById(noteIdInt)
    }
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TopActionBar(
                onBackPressed = { onBackPressed(isNewNote, note, notesViewModel, navigator) }
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                TitleRow(noteTitle = note?.title) { userInput: String ->
                    note = note?.copy(title = userInput)
                        ?: Note(null, userInput, "", 123456)
                }
                NoteRow(noteBody = note?.body) { userInput: String ->
                    note = note?.copy(body = userInput)
                        ?: Note(null, "", userInput, 123456)
                }
            }
            BottomActionBar()
        }
    }
}

private fun onBackPressed(
    isNewNote: Boolean,
    note: Note?,
    notesViewModel: NotesViewModel,
    navigator: DestinationsNavigator
) {
    if (isNewNote) {
        note?.let { nnNote ->
            notesViewModel.insertNote(note = nnNote)
            navigator.navigateUp()
        }
    } else {
        note?.let { nnNote ->
            notesViewModel.updateNote(note = nnNote)
            navigator.navigateUp()
        }
    }
}
