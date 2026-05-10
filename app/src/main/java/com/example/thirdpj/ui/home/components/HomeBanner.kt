package com.example.thirdpj.ui.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirdpj.ui.theme.ThirdPJTheme
import org.w3c.dom.Text

@Composable
fun HomeBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        // RoundedCornerShape으로 둥근 사각형 만들 수 있음
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1715))
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                // column을 하나 추가해서 div처럼 나눌 수 있음!
                Column{
                    Text(
                        text = "이번주 BEST",
                        color = Color(0xFFF7D070),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp) )

                    Text(
                        text = "남의 갓생,\n 내 일정으로 포크하기",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 28.sp

                    )
                }

                // 지금 둘러보기 버튼
                // Button으로 만들어야 되려나?



            }

        }
    }
}

@Preview
@Composable
fun HomeBannerPreview() {
    ThirdPJTheme {
        HomeBanner()
    }
}