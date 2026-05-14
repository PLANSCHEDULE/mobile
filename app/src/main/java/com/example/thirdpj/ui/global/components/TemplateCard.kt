package com.example.thirdpj.ui.global.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirdpj.R
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun TemplateCard(modifier: Modifier = Modifier,
                 title: String = "일본 당일치기 도쿄",
                 authorName: String = "길동",
                 authorHandle: String = "@gildong",
                 likeCount: String = "1234",
                 downloadCount: String = "123",
                 schedules: List<Pair<String, String>> = listOf("09:00" to "김포공항", "12:00" to "점심식사")) {
    Card(
        modifier = modifier
            .padding(4.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)


    ) {
        // 상단 영역( 이미지 or 배경)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0xFFFFB380))
                .padding(10.dp)
        ) {
            // 다운로드 버튼
            Surface(
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.TopStart),
                shape = CircleShape
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.download),
                    contentDescription = null,
                    modifier = Modifier.padding(6.dp),
                    tint = Color.DarkGray

                )
            }

            // 찜 버튼
            Surface(
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.TopEnd),
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.8f)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    modifier = Modifier.padding(6.dp),
                    tint = Color(0xFFFF6B6B)
                )
            }

        }

        // 정보 및 일정 영역
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .defaultMinSize(minHeight = 140.dp)

        ) {
            // 제목
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp

            )

            Spacer(modifier = Modifier.height(2.dp))

            // 사용자 정보
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(18.dp),
                    shape = CircleShape,
                    color = Color(0xFFB39DDB)
                ) {
                    Box(contentAlignment = Alignment.Center) {

                        Text(
                            text = authorName.take(1),
                            color = Color.White,
                            fontSize = 6.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = authorHandle,
                    color = Color.Gray,
                    fontSize = 10.sp
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 10.dp),
                thickness = 0.5.dp
            )
            
            // 일정 내용 구현 자리 필요!
            schedules.forEach { (time, location) ->
                Row(modifier = Modifier.padding(vertical = 1.dp)) {
                    Text(text = time,
                        fontSize = 10.sp,
                        color = Color.Gray,
                        modifier = Modifier.width(35.dp)
                    )
                    Text(text = "•",
                        fontSize = 10.sp,
                        color = Color.LightGray,
                        modifier = Modifier.padding(horizontal = 4.dp))
                    Text(text = location, fontSize = 10.sp, color = Color.DarkGray, maxLines = 1)
                }
            }
        }

        // 통계 영역 (통계 영역을 나중에 구현하는게 좋으려나. 한 번 생각해 보기)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFAFAFA))
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier
                    .size(12.dp),
                tint = Color.LightGray
            )
            // DB연결 시
            Text(text = likeCount,
                fontSize = 12.sp,
                modifier = Modifier.padding(6.dp)
            )

            Spacer(modifier = Modifier
                .width(16.dp)
            )

            Icon(
                painter = painterResource(id = R.drawable.download),
                contentDescription = null,
                modifier = Modifier.padding(6.dp),
                tint = Color.LightGray

            )

            Text(text = downloadCount,
                fontSize = 12.sp,
                modifier = Modifier.padding(6.dp))


        }

    }
}

@Preview(name = "기본 카드", showBackground = true)
@Composable
fun TemplateCardPreview() {
    ThirdPJTheme {
        TemplateCard()
    }
}

@Preview(name = "긴 제목 카드", showBackground = true)
@Composable
fun LongTitleCardPreview() {
    ThirdPJTheme {
        TemplateCard(
            title = "제주도 여행기제주도 여행기제주도 여행기제주도 여행기제주도 여행기제주도 여행기제주도 여행기제주도 여행기",
            authorName = "홍길동",
            authorHandle = "@hong_gildong",
            likeCount = "999",
            schedules = listOf(
                "08:00" to "제주도 도착",
                "10:00" to "스카이라이너 탑승",
                "12:00" to "맛집 투어"
            )
        )
    }
}