package com.mutualmobile.mmnotes.android.ui.screens.note_details_screen.compoenents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
fun TitleRow(
    padding: Dp = 8.dp
) {
    Box(
        modifier = Modifier
            .padding(horizontal = padding * 3, vertical = padding)
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.TopStart
    ) {
        var title by remember {
            mutableStateOf("")
        }
        BasicTextField(
            value = title,
            onValueChange = { userInputText ->
                title = userInputText
            },
            textStyle = MaterialTheme.typography.h4.copy(color = MaterialTheme.colors.onSurface)
        )
        Text(
            text = stringResource(R.string.titleRow_hintTxt),
            modifier = Modifier.alpha(if (title.isEmpty()) 1f else 0f),
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
        )
    }
}
