package com.mutualmobile.mmnotes.android.ui.screens.note_details_screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.mutualmobile.mmnotes.android.ui.screens.note_details_screen.compoenents.BottomActionBar
import com.mutualmobile.mmnotes.android.ui.screens.note_details_screen.compoenents.NoteRow
import com.mutualmobile.mmnotes.android.ui.screens.note_details_screen.compoenents.TitleRow
import com.mutualmobile.mmnotes.android.ui.screens.note_details_screen.compoenents.TopActionBar
import com.mutualmobile.mmnotes.domain.models.Note
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalAnimationApi::class)
@Destination
@Composable
fun NoteDetailsScreen(
    navigator: DestinationsNavigator,
    noteId: String? = null
) {
    var note: Note? by remember {
        mutableStateOf(null)
    }

    noteId?.let {
        val noteIdInt = it.toInt()
        // todo: Find the actual note using this ID
    }

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TopActionBar(navigator)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                TitleRow(noteTitle = note?.title)
                NoteRow(noteBody = note?.body)
            }
            BottomActionBar()
        }
    }
}
