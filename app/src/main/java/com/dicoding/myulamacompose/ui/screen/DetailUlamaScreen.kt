package com.dicoding.myulamacompose.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.dicoding.myulamacompose.R
import com.dicoding.myulamacompose.data.UlamaRepository
import com.dicoding.myulamacompose.vm.DetailUlamaVM
import com.dicoding.myulamacompose.vm.VMFactory

@Composable
fun DetailUlamaScreen(
    modifier: Modifier = Modifier,
    idUlama: String,
    navigateUp: () -> Unit,
    viewModel: DetailUlamaVM = viewModel(
        factory = VMFactory(
            UlamaRepository(),
        ),
    )
) {
    val dataUlama by viewModel.getDataUlama(idUlama).collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                backgroundColor = Color.Transparent,
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    IconButton(
                        onClick = navigateUp
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.back),
                            tint = MaterialTheme.colors.primary
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.detail),
                        style = MaterialTheme.typography.h1,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colors.primary
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(model = dataUlama.photoUrl, contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            )
            Spacer(modifier = Modifier.padding(12.dp))
            Text(text = dataUlama.name,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h3.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(text = dataUlama.description,
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

        }
    }
}