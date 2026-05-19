package com.example.thirdpj.ui.search.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchEmptyView(
    isSearched: Boolean
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = if (isSearched) "🔍" else "✏️",
                fontSize = 40.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = if (isSearched) "검색 결과가 없습니다." else "검색어를 입력해주세요.",
                color = Color(0xFFAFA9EC),
                fontSize = 14.sp
            )
        }
    }
}