package com.dicoding.myulamacompose.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.myulamacompose.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Preview
fun AboutMeScreen(
    modifier: Modifier = Modifier,
) {
    val myPhoto = painterResource(id = R.drawable.my_photo)
    val name = stringResource(id = R.string.name)
    val email = stringResource(id = R.string.email)

    Scaffold {
        Column(modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = myPhoto,
                contentDescription = stringResource(R.string.profile_image),
                modifier = Modifier.size(250.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(12.dp))
            Text(
                text = name,
                fontSize = 24.sp,
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(
                text = email,
                fontSize = 14.sp,
                style = MaterialTheme.typography.h3
            )
        }
    }
}