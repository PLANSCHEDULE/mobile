package com.example.thirdpj.ui.allview.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirdpj.data.post.dto.PostTemplateDto
import com.example.thirdpj.data.post.dto.PostTemplateItem
import com.example.thirdpj.ui.global.components.PostTemplateCard
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun TemplateGrid(
    modifier: Modifier,
    templates: List<PostTemplateDto>,
    onCardClick: (Long) -> Unit = {},
    onDownloadClick: (Long) -> Unit = {},
    onFavoriteClick: (Long) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        // modifier 인자로 안오는데 / 변수명 modifier로 변경 Modifer로 해서 안됐음.
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {
       items(templates, key = {it.postTemplateId}) { template ->
           PostTemplateCard(
               template = template,
               modifier = Modifier.clickable{onCardClick(template.postTemplateId)},
               onDownloadClick = onDownloadClick,
               onFavoriteClick = onFavoriteClick

           )

       }
    }
}

@Preview(showBackground = true)
@Composable
fun TemplateGridPreview() {
    // 💡 프리뷰용 더미 데이터도 새 DTO 구조에 맞게 보정
    val mockData = listOf(
        PostTemplateDto(
            postTemplateId = 1L,
            title = "도쿄 당일치기",
            background = null,
            authorHandle = "@gildong",
            favoriteCount = 1200,
            downloadCount = 100,
            isFavorite = false,
            items = listOf(
                PostTemplateItem("09:00", "공항", 1),
                PostTemplateItem("12:00", "라멘", 2)
            )
        ),
        PostTemplateDto(
            postTemplateId = 2L,
            title = "제주도 2박 3일",
            background = null,
            authorHandle = "@chulsoo",
            favoriteCount = 850,
            downloadCount = 45,
            isFavorite = true,
            items = listOf(
                PostTemplateItem("10:00", "공항", 1),
                PostTemplateItem("13:00", "고기국수", 2)
            )
        )
    )
    ThirdPJTheme {
        TemplateGrid(
            modifier = Modifier.fillMaxSize(),
            templates = mockData
        )
    }
}