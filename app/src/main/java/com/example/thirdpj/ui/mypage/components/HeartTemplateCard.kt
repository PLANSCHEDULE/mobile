package com.example.thirdpj.ui.mypage.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirdpj.R
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun HeartTemplateCard(
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F3FF)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 하트 박스
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF7F77DD)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.bookmark_heart),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // 중간 텍스트 2 영역이니까 Column? Row는 아닌 듯..
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    // 그냥 row로 하면 뭔가 애매한데
                    // 찾아보니 buildAnnotatedString 사용하면 줄 정렬, 바꿈이 더 자연스럽게 되는게 있음, text = 에 넣어서 사용하면 되는 듯
                    text = buildAnnotatedString {
                        append("하트 누른 템플릿 ")
                        //오 이렇게 적용하니까 디자인이랑 거의 비슷하게 됨
//                        withStyle(style = SpanStyle(color = Color(0xFF534AB7), fontWeight = FontWeight.Bold)) {
//                            append("32")
//                        }
                    },
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF534AB7)
                )

                Spacer(modifier = Modifier.height(2.dp))

                // 설명
                Text(
                    text = "찜한 일정 모아보기",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF7F77DD)
                )
            }

            // 화살표 아이콘
            Icon(
                painter = painterResource(id = R.drawable.chevron_right),
                contentDescription = null,
                tint = Color(0xFFAFA9EC),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview
@Composable
fun HeartTemplatePreview() {
    ThirdPJTheme {
        HeartTemplateCard(onClick = {})
    }
}