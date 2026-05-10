package com.example.thirdpj.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirdpj.R
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun TemplateCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)


    ) {
        // 상단 영역( 이미지 or 배경)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(Color(0xFFFFB380))
                .padding(12.dp)
        ) {
            // 다운로드 버튼
            Surface(
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.TopStart),
                shape = CircleShape
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.download_icon),
                    contentDescription = null,
                    modifier = Modifier.padding(6.dp),
                    tint = Color.DarkGray

                )
            }

            // 찜 버튼
            Surface(
                modifier = Modifier
                    .size(32.dp)
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
                .padding(16.dp)
                .defaultMinSize(minHeight = 180.dp)

        ) {

        }

        // 통계 영역 (통계 영역을 나중에 구현하는게 좋으려나. 한 번 생각해 보기)

    }
}

@Preview
@Composable
fun TemplateCardPreview() {
    ThirdPJTheme {
        TemplateCard()
    }
}