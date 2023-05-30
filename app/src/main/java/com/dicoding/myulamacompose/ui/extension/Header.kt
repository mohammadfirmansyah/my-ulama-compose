package com.dicoding.myulamacompose.ui.extension

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Header(
    char: Char,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = modifier
    ) {
        Text(
            text = char.toString(),
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}