package com.example.thirdpj.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun HomeHeader() {
    // 파일 구조를 어떻게 잡아야되지
    Column() {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "홍길동",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black

            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeHeaderPreview() {
    ThirdPJTheme{
        HomeHeader()
    }

}