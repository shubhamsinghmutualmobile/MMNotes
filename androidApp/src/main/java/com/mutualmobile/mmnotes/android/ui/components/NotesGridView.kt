package com.mutualmobile.mmnotes.android.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
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

@ExperimentalFoundationApi
@Composable
fun NotesGridView(
    notes: List<Note>,
    gridSize: Int = 2,
    padding: Dp = 4.dp
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
                            title = note.title,
                            body = note.body,
                            padding = padding
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

@Composable
private fun NoteCard(
    padding: Dp,
    title: String,
    body: String
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
                .clickable {}
                .padding(padding * 3),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = title, style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.padding(padding / 2))
            Text(text = body, style = MaterialTheme.typography.body2)
        }
    }
}
