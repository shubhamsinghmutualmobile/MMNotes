package com.mutualmobile.mmnotes.android.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mutualmobile.mmnotes.android.R

@Composable
fun TopSearchBar(
    padding: Dp = 4.dp
) {
    Card(
        shape = RoundedCornerShape(padding * padding.value * 2),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = padding * padding.value, vertical = padding * 2)
            .wrapContentHeight(),
        elevation = padding
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {},
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier.padding(start = padding * 2)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = stringResource(R.string.topSearchBar_menuDesc)
                )
            }
            Text(
                text = stringResource(R.string.topSearchBar_searchHint),
                style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {},
                modifier = Modifier.padding(end = padding)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_stack),
                    contentDescription = stringResource(R.string.topSearchBar_layoutTypeDesc)
                )
            }
            Card(
                shape = CircleShape,
                modifier = Modifier.padding(end = padding * padding.value)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = stringResource(R.string.topSearchBar_profileBtnDesc),
                    modifier = Modifier
                        .clickable {}
                        .size(padding * padding.value * 2.2f),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
