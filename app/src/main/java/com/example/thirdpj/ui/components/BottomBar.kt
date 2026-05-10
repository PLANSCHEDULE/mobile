package com.example.thirdpj.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun BottomBar() {

    // 임시 변수 (ui 용)
    val selectedTab = "HOME"


    NavigationBar(
        containerColor = Color.White
    ) {
        // 홈
        NavigationBarItem(
            selected = selectedTab == "HOME",
            onClick = {},
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("HOME") }
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