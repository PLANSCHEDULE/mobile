package com.example.thirdpj.ui.allview.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.thirdpj.ui.allview.components.AllViewTopBar
import com.example.thirdpj.ui.allview.components.TemplateGrid
import com.example.thirdpj.ui.testdata.TemplateItemData
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun TemplateAllViewScreen(
    title: String,
    templates: List<TemplateItemData>,
    onBackClick: () -> Unit,
    onCardClick: (Int) -> Unit = {}
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
            TemplateGrid(
                modifier = Modifier,
                template = templates,
                onCardClick = onCardClick
            )
        }

    }
    // 음.. 템플릿이 없다면 어떻게 처리해야될 지도 생각해 보야될 듯


}

@Preview
@Composable
fun TemplateAllViewScreenPreview() {
    val dummyList = listOf(
        TemplateItemData(1, "일본 당일치기 도쿄", "길동", "@gildong", "1234", "123", listOf("09:00" to "공항", "12:00" to "식사")),
        TemplateItemData(2, "제주도 힐링 여행", "철수", "@chulsoo", "500", "50", listOf("10:00" to "제주공항", "14:00" to "카페")),
        TemplateItemData(3, "서울 밤도깨비 야시장", "영희", "@younghee", "2.5k", "400", listOf("18:00" to "여의도", "20:00" to "푸드트럭")),
        TemplateItemData(4, "속초 만석닭강정 투어", "미애", "@miae", "99", "10", listOf("11:00" to "중앙시장", "15:00" to "속초해변")),
        TemplateItemData(5, "전주 한옥마을 정복", "도령", "@doryung", "1.1k", "220", listOf("12:00" to "경기전", "14:00" to "비빔밥")),
        TemplateItemData(6, "경주 황리단길 산책", "박사", "@doctor", "880", "150", listOf("10:00" to "첨성대", "13:00" to "십원빵"))
    )

    ThirdPJTheme {
        TemplateAllViewScreen(
            title = "찜한 템플릿",
            templates = dummyList,
            onBackClick = {}
        )
    }
}