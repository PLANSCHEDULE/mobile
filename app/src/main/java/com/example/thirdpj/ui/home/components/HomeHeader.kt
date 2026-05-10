package com.example.thirdpj.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun HomeHeader() {
    // 파일 구조를 어떻게 잡아야되지
    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            // 이게 있어야 검색 아이콘과 텍스트간의 공간이 생김
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "홍길동",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )

            // 검색 아이콘
            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "검색",
                    modifier = Modifier.size(28.dp)
                )
            }
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