package com.example.thirdpj.ui.search.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thirdpj.ui.search.SearchViewModel
import com.example.thirdpj.ui.search.components.SearchBar
import com.example.thirdpj.ui.search.components.SearchEmptyView
import com.example.thirdpj.ui.search.components.SearchResultGrid

@Composable
fun SearchScreen(
    onCardClick: (Long) -> Unit = {},
    viewModel: SearchViewModel = viewModel()
) {
    val results by viewModel.results.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F5F0))
    ) {
        SearchBar(
            keyword = viewModel.keyword,
            onKeywordChange = { viewModel.onKeywordChange(it) },
            onSearch = { viewModel.search(isRefresh = true) }
        )

        when {
            isLoading && results.isEmpty() -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color(0xFFEF7F61))
                }
            }

            viewModel.isSearched && results.isEmpty() -> {
                SearchEmptyView(isSearched = true)
            }

            !viewModel.isSearched -> {
                SearchEmptyView(isSearched = false)
            }
            else -> {
                SearchResultGrid(
                    templates = results,
                    isLoading = isLoading,
                    onCardClick = onCardClick,
                    onDownloadClick = { id -> viewModel.downloadTemplate(id) },
                    onFavoriteClick = { id -> viewModel.toggleFavorite(id) },
                    onLoadMore = { viewModel.search() }
                )
            }
        }
    }
}