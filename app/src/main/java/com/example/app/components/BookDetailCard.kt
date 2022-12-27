package com.example.app.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.flowlayout.FlowRow


@Composable
fun BookDetailCard(
    title: String,
    authors: List<String>,
    thumbnailUrl: String,
    categories: List<String>
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 12.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(color = Color.Cyan.copy(0.1f)),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            AsyncImage(
                model = thumbnailUrl,
                contentDescription =title,
                modifier = Modifier
                    .size(240.dp, 140.dp)
                    .padding(12.dp)
                //  .offset(y = (-30).dp)
            )



            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = authors.toString(),
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.Center,
                    color = Color.Black.copy(0.7f)
                )

                Spacer(modifier = androidx.compose.ui.Modifier.height(8.dp))

                Text(
                    text = title,
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    color = Color.Black.copy(0.7f)
                )

                Spacer(modifier = androidx.compose.ui.Modifier.height(12.dp))
                FlowRow {
                    categories.forEach {
                        ChipView(category = it)
                    }

                }
            }


        }
    }

}
