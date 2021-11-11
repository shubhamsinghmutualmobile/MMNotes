package com.mutualmobile.mmnotes.android.ui.screens.note_details_screen.compoenents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mutualmobile.mmnotes.android.R

@Composable
fun NoteRow(
    padding: Dp = 8.dp,
    noteBody: String?,
    updateNoteBody: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = padding * 3, vertical = padding)
            .fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        var noteText by remember {
            mutableStateOf(noteBody ?: "")
        }
        BasicTextField(
            modifier = Modifier.fillMaxSize(),
            value = noteText,
            onValueChange = { userInputText ->
                noteText = userInputText
                updateNoteBody(userInputText)
            },
            textStyle = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.onSurface)
        )
        Text(
            text = stringResource(R.string.noteRow_hintTxt),
            modifier = Modifier.alpha(if (noteText.isEmpty()) 1f else 0f),
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
        )
    }
}
