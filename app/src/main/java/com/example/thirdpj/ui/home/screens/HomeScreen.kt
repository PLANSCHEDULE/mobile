package com.example.thirdpj.ui.home.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thirdpj.ui.global.components.PostTemplateCard
import com.example.thirdpj.ui.home.components.HomeBanner
import com.example.thirdpj.ui.home.components.HomeHeader
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun HomeScreen(
    onBrowseClick: () -> Unit = {},
    onCardClick: (Long) -> Unit = {},
    onSearchClick: () -> Unit = {},
    viewModel: HomeViewModel = viewModel{ HomeViewModel() }
) {
    val templates by viewModel.templates.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED){
            Log.d("HOME_REFRESH", "새로고침 호출됨") // 찜이 잘 안되서 확인하기 위한 log
            viewModel.fetchTemplates(isRefresh = true)
        }
    }

    Scaffold(
        topBar = {
            HomeHeader(onSearchClick = onSearchClick)
        },
        // 하단 바 수정하면서 navigate 설정해 주면서 오류남
        // MainActivity에서 구현함으로써 homescreen에서는 삭제
        // 근데 mainActivity에서 설정을 해두었음
        containerColor = Color(0xFFF5F3FF)
    ) {innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 15.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
                    HomeBanner(onBrowseClick = onBrowseClick)
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "공유된 템플릿",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color(0xFF534AB7)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            items(
                count = templates.size,
                key = { templates[it].postTemplateId }
            ) { index ->
                // 마지막 2개 남으면 다음 페이지 로드
                if (index >= templates.size - 2) {
                    LaunchedEffect(index) { viewModel.fetchTemplates() }
                }
                PostTemplateCard(
                    template = templates[index],
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable{onCardClick(templates[index].postTemplateId)},
                    onDownloadClick = { id -> viewModel.downloadTemplate(id) },
                    onFavoriteClick = { id -> viewModel.toggleFavorite(id) }
                )
            }

            // 로딩 표시
            if (isLoading) {
                item(span = { GridItemSpan(2) }) {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color(0xFFEF7F61))
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    ThirdPJTheme {
        HomeScreen()
    }
}