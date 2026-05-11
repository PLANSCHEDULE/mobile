package com.example.thirdpj.ui.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun ProfileHeaderScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Yellow)
                .padding(horizontal = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 40.dp, bottom = 60.dp)
            ) {
                // 프로필 이미지 영역
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .border(2.dp, Color.White.copy(alpha = 0.5f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "가",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFF8E5E)
                    )


                }
            }
        }
    }
}

@Preview
@Composable
fun ProfileHeaderPreview() {
    ThirdPJTheme {
        ProfileHeaderScreen()
    }
}