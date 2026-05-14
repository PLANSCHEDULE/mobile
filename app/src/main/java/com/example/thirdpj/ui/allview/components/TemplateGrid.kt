package com.example.thirdpj.ui.allview.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirdpj.ui.global.components.TemplateCard
import com.example.thirdpj.ui.testdata.TemplateItemData
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun TemplateGrid(
    modifier: Modifier,
    template: List<TemplateItemData>,
    onCardClick: (Int) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {
       items(template, key = {it.id}) { template ->
           TemplateCard(
               modifier = Modifier.clickable{onCardClick(template.id)},
               title = template.title,
               authorName = template.authorName,
               authorHandle = template.authorHandle,
               likeCount = template.likeCount,
               downloadCount = template.downloadCount,
               schedules = template.schedules
           )

       }
    }
}

@Preview(showBackground = true)
@Composable
fun TemplateGridPreview() {
    val mockData = listOf(
        TemplateItemData(1, "도쿄 당일치기", "길동", "@gildong", "1.2k", "100", listOf("09:00" to "공항", "12:00" to "라멘")),
        TemplateItemData(2, "제주도 2박 3일", "철수", "@chulsoo", "850", "45", listOf("10:00" to "공항", "13:00" to "고기국수")),
        TemplateItemData(3, "서울 야경 코스", "영희", "@younghee", "2.1k", "300", listOf("19:00" to "남산", "21:00" to "한강")),
        TemplateItemData(4, "부산 먹방 투어", "길동", "@gildong", "999", "120", listOf("11:00" to "국밥", "15:00" to "밀면")),
        TemplateItemData(5, "강릉 바다 여행", "미애", "@miae", "500", "30", listOf("08:00" to "안목해변", "12:00" to "물회")),
        TemplateItemData(6, "경주 역사 탐방", "박사", "@doctor", "1.5k", "200", listOf("09:00" to "불국사", "14:00" to "황리단길"))
    )
    ThirdPJTheme {

        TemplateGrid(
            modifier = Modifier.fillMaxSize(),
            template = mockData
        )


    }

}