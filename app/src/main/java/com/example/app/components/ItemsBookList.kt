package com.example.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.flowlayout.FlowRow


@Composable
fun ItemBookList(
    title: String,
    author: String,
    thumbnailUrl: String,
    categories: List<String>,
    onItemClick: () -> Unit
) {
    Card(modifier = Modifier
        .clickable(onClick = onItemClick)
        .fillMaxWidth()
        .wrapContentHeight()
        .background(color=Color.Cyan.copy(0.1f))
        .clip(RoundedCornerShape(12.dp))
        .padding(16.dp)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.onPrimary),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            AsyncImage(
                model = thumbnailUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(98.dp, 145.dp)
                    .padding(12.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = "by".plus(author),
                    style = MaterialTheme.typography.caption,
                    color = Color.Black.copy(0.7f)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = title,
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.Black.copy(0.7f)
                )

                Spacer(modifier = Modifier.height(12.dp))
                FlowRow {
                    categories.forEach {
                        ChipView(category = it)
                    }

                }
            }
        }
    }
}
    @Composable
    fun ChipView(category: String) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(12.dp))
                .background(MaterialTheme.colors.primary.copy(.10F))
                .padding(start = 12.dp, end = 12.dp, top = 5.dp, bottom = 5.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = category, style = MaterialTheme.typography.caption,
                color = Color.Black.copy(0.7f),
                modifier = Modifier.padding(5.dp)
            )
        }
    }


