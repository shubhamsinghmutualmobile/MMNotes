package com.mutualmobile.mmnotes.android.ui.screens.note_details_screen.compoenents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.mutualmobile.mmnotes.android.R

@Composable
fun BottomActionBar(
    noteEditTime: String? = stringResource(R.string.noteDetailsScreen_sampleNoteEditTimeTxt)
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add_box),
                contentDescription = stringResource(
                    R.string.noteDetailsScreen_attachElementsDesc
                )
            )
        }
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.ic_color_lens),
                contentDescription = stringResource(R.string.noteDetailsScreen_changeColorDesc)
            )
        }
        Text(
            text = noteEditTime?.let { nnNoteEditTime -> "Created on: $nnNoteEditTime" } ?: "",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.button
        )
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.ic_overflow),
                contentDescription = stringResource(
                    R.string.noteDetailsScreen_overflowMenuDesc
                )
            )
        }
    }
}
