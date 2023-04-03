package com.instance.dreamjournal.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

import com.instance.dreamjournal.MySurface
import com.instance.dreamjournal.Recording
import com.instance.dreamjournal.blue700
import com.instance.dreamjournal.grey700

@Composable
fun RecordingCard(
    recording: Recording,
    onClickListener: () -> Unit
) {

    MySurface(
        onClick = onClickListener,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .weight(8f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = recording.readableDayTime,
                        style = MaterialTheme.typography.h6,
                    )
                    Text(
                        text = recording.readableDate,
                        style = MaterialTheme.typography.body1,
                        color = grey700
                    )
                }
                Text(
                    text = recording.duration,
                    style = MaterialTheme.typography.body1,
                    color = blue700
                )

            }
            Icon(

                Icons.Rounded.PlayArrow,

                modifier = Modifier
                    .padding(end = 16.dp, bottom = 16.dp)
                    .requiredWidth(36.dp)
                    .weight(1f)
                    .align(Alignment.Bottom),

                contentDescription = null
            )
        }
    }
}