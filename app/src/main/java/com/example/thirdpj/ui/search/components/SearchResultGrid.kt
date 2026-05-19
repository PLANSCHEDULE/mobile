package com.example.thirdpj.ui.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.thirdpj.data.post.dto.PostTemplateDto
import com.example.thirdpj.ui.global.components.PostTemplateCard

@Composable
fun SearchResultGrid(
    templates: List<PostTemplateDto>,
    isLoading: Boolean,
    onCardClick: (Long) -> Unit = {},
    onDownloadClick: (Long) -> Unit = {},
    onFavoriteClick: (Long) -> Unit = {},
    onLoadMore: () -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(templates, key = { it.postTemplateId }) { template ->
            // 마지막 2개 남으면 다음 페이지 로드
            if (templates.indexOf(template) >= templates.size - 2) {
                LaunchedEffect(template.postTemplateId) { onLoadMore() }
            }
            PostTemplateCard(
                template = template,
                modifier = Modifier.clickable { onCardClick(template.postTemplateId) },
                onDownloadClick = onDownloadClick,
                onFavoriteClick = onFavoriteClick
            )
        }

        // 로딩 표시
        if (isLoading) {
            item(span = { GridItemSpan(2) }) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFFEF7F61))
                }
            }
        }
    }
}