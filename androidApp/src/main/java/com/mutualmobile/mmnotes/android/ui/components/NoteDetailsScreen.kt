package com.mutualmobile.mmnotes.android.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun NoteDetailsScreen(
    navigator: DestinationsNavigator
) {
    Text(text = "NoteDetailsScreen", modifier = Modifier.clickable { navigator.navigateUp() })
}
