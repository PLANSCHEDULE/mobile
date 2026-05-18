package com.example.thirdpj.ui.favorite.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thirdpj.ui.allview.components.TemplateGrid
import com.example.thirdpj.ui.favorite.FavoriteViewModel

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = viewModel(),
) {
    val templates by viewModel.templates.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    // 화면 돌아올 때마다 새로고침
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            viewModel.fetchMyFavorites(isRefresh = true)
        }
    }

    Scaffold(
        containerColor = Color(0xFFF9F5F0)
    ) { innerPadding ->
        if (isLoading && templates.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFFEF7F61))
            }
        } else if (templates.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "찜한 템플릿이 없습니다.",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        } else {
            TemplateGrid(
                modifier = Modifier.padding(innerPadding),
                templates = templates,
                onDownloadClick = { id -> viewModel.downloadTemplate(id) },
                onFavoriteClick = { id -> viewModel.toggleFavorite(id) }
            )
        }
    }

}
