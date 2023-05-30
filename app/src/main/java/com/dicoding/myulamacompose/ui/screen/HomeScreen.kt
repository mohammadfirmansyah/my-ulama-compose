package com.dicoding.myulamacompose.ui.screen

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.myulamacompose.data.UlamaRepository
import com.dicoding.myulamacompose.ui.extension.Header
import com.dicoding.myulamacompose.ui.extension.ListUlama
import com.dicoding.myulamacompose.ui.extension.Search
import com.dicoding.myulamacompose.ui.extension.UpButton
import com.dicoding.myulamacompose.ui.theme.MyUlamaComposeTheme
import com.dicoding.myulamacompose.vm.HomeVM
import com.dicoding.myulamacompose.vm.VMFactory
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeVM = viewModel(factory = VMFactory(
        UlamaRepository()
    )
    ),
    navigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val groupUlama by viewModel.groupUlama.collectAsState()
    val query by viewModel.query
    Box(modifier = modifier) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showButton: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item {
                Search(
                    query = query,
                    onQueryChange = viewModel::search,
                    modifier = Modifier.background(MaterialTheme.colors.primary)
                )
            }
            groupUlama.forEach { (initial, ulama) ->
                stickyHeader {
                    Header(initial)
                }
                items(ulama, key = { it.id }) { ulama ->
                    ListUlama(
                        id = ulama.id,
                        name = ulama.name,
                        photoUrl = ulama.photoUrl,
                        navigateToDetail = navigateToDetail,
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItemPlacement(tween(durationMillis = 100))
                    )

                }
            }
        }

        AnimatedVisibility(
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            visible = showButton,
            modifier = Modifier
                .padding(bottom = 30.dp)
                .align(Alignment.BottomCenter)
        ) {
            UpButton(
                onClick = {
                    scope.launch {
                        listState.scrollToItem(index = 0)
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MyUlamaComposeTheme {
        HomeScreen(navigateToDetail = {})
    }
}