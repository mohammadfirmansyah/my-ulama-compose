package com.dicoding.myulamacompose.ui.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ListUlama(
    id: String,
    name: String,
    photoUrl: String,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
) {
    Box(
        modifier = modifier
            .clickable {
                navigateToDetail(id)
            }
            .padding(8.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = "Foto Ulama",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(60.dp)
            )
            Text(
                text = name,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}