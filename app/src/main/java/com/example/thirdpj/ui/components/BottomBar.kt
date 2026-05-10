package com.example.thirdpj.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun BottomBar() {

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        // 홈
        NavigationBarItem(
            // 우선 ui 틀 잡는 단계에서는 bolean으로 두는게 좋은 거 같다
            selected = true,
            onClick = {},
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("HOME") }
        )

        // 탐색
        // 음 탐색 기능 대신 전체적인 템플릿을 볼 수 있는 메뉴여도 괜찮을 듯
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {Icon(Icons.Default.Search, contentDescription = null)},
            label = {Text("탐색")}
        )

        // 찜
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {Icon(Icons.Default.Favorite, contentDescription = null)},
            label = {Text("찜")}
        )



    }
}

@Preview
@Composable
fun BottomBarPreview() {
    ThirdPJTheme {
        BottomBar()
    }
}