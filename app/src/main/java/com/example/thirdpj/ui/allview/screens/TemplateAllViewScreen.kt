package com.example.thirdpj.ui.allview.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.thirdpj.data.post.dto.PostTemplateDto
import com.example.thirdpj.data.post.dto.PostTemplateItem
import com.example.thirdpj.ui.allview.components.AllViewTopBar
import com.example.thirdpj.ui.allview.components.TemplateGrid
import com.example.thirdpj.ui.testdata.TemplateItemData
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun TemplateAllViewScreen(
    title: String,
    templates: List<PostTemplateDto>,
    onBackClick: () -> Unit,
    onCardClick: (Long) -> Unit = {}
) {
    Scaffold(
        topBar = {
            AllViewTopBar(
                title = title,
                onBackClick = onBackClick
            )
        },
        containerColor = Color(0xFFF9F5F0)

    ) {innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            if (templates.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "아직 저장된 템플릿이 없습니다.",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            } else {
                TemplateGrid(
                    modifier = Modifier,
                    templates = templates,
                    onCardClick = onCardClick
                )
            }
        }

    }
    // 음.. 템플릿이 없다면 어떻게 처리해야될 지도 생각해 보야될 듯


}

@Preview(name = "데이터가 있을 때", showBackground = true)
@Composable
fun TemplateAllViewScreenPreview() {
    val dummyList = listOf(
        PostTemplateDto(
            postTemplateId = 1L, title = "일본 당일치기 도쿄", background = null, authorHandle = "@gildong",
            favoriteCount = 1234, downloadCount = 123, isFavorite = true,
            items = listOf(PostTemplateItem("09:00", "공항", 1), PostTemplateItem("12:00", "식사", 2))
        ),
        PostTemplateDto(
            postTemplateId = 2L, title = "제주도 힐링 여행", background = null, authorHandle = "@chulsoo",
            favoriteCount = 500, downloadCount = 50, isFavorite = true,
            items = listOf(PostTemplateItem("10:00", "제주공항", 1), PostTemplateItem("14:00", "카페", 2))
        )
    )

    ThirdPJTheme {
        TemplateAllViewScreen(
            title = "찜한 템플릿",
            templates = dummyList,
            onBackClick = {}
        )
    }
}

@Preview(name = "데이터가 없을 때 (텅 비었을 때)", showBackground = true)
@Composable
fun EmptyTemplateAllViewScreenPreview() {
    ThirdPJTheme {
        TemplateAllViewScreen(
            title = "찜한 템플릿",
            templates = emptyList(),
            onBackClick = {}
        )
    }
}