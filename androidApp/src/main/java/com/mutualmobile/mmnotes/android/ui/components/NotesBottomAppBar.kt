package com.mutualmobile.mmnotes.android.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.mutualmobile.mmnotes.android.R

@Composable
fun NotesBottomAppBar(fabShape: RoundedCornerShape) {
    BottomAppBar(
        cutoutShape = fabShape,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.ic_checkbox),
                contentDescription = stringResource(R.string.bottomAppBarCb_description)
            )
        }
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.ic_brush),
                contentDescription = stringResource(R.string.bottomAppBarBrush_description)
            )
        }
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.ic_mic),
                contentDescription = stringResource(R.string.bottomAppBarMic_description)
            )
        }
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(id = R.drawable.ic_image),
                contentDescription = stringResource(R.string.bottomAppBarPhoto_description)
            )
        }
    }
}
