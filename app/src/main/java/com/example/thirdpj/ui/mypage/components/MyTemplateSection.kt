package com.example.thirdpj.ui.mypage.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirdpj.data.template.dto.TemplateResponse
import com.example.thirdpj.ui.global.components.MyTemplateCard
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun MyTemplateSection(
    title: String,
    count: String,
    countColor: Color,
    templates: List<TemplateResponse> = emptyList(),
    onViewAllClick: () -> Unit = {},
    onCardClick: (Long) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        // 텍스트 영역
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildAnnotatedString {
                    append("$title ")
                    // 나중에 db에서 값 받아와야 됨
                    withStyle(style = SpanStyle(color = countColor,
                        fontWeight = FontWeight.Bold)) {
                        append(count)
                    }
                },
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "전체보기 >",
                fontSize = 13.sp,
                color = Color.Gray,
                modifier = Modifier.clickable{

                }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 카드 영역
        if (templates.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "아직 템플릿이 없습니다.",
                    color = Color.Gray,
                    fontSize = 13.sp
                )
            }
        } else {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(templates, key = { it.id }) { template ->
                    MyTemplateCard(
                        template = template,
                        onClick = { onCardClick(template.id) }
                    )
                }
            }
        }

    }
}




@Preview
@Composable
fun MyTemplateSectionPreview() {
    ThirdPJTheme {
        MyTemplateSection(
            title = "내 템플릿",
            count = "3",
            countColor = Color.Yellow
        )
    }
}