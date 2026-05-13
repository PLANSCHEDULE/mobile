package com.example.thirdpj.ui.mypage.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirdpj.ui.components.BottomBar
import com.example.thirdpj.ui.mypage.components.HeartTemplateCard
import com.example.thirdpj.ui.mypage.components.MyTemplateSection
import com.example.thirdpj.ui.mypage.components.ProfileHeaderScreen
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun ProfileScreen() {
    Scaffold(
        bottomBar = { BottomBar() }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFFDF9F6))
        ) {
            ProfileHeaderScreen()

            Spacer(modifier = Modifier.height(16.dp))

            HeartTemplateCard()

            Spacer(modifier = Modifier.height(24.dp))

            MyTemplateSection(
                title = "내 템플릿",
                count = "3",
                countColor = Color(0xFFFF5252)
            )

            Spacer(modifier = Modifier.height(24.dp))

            MyTemplateSection(
                title = "포크한 템플릿",
                count = "2",
                countColor = Color(0xFF4A90E2)
            )

            Spacer(modifier = Modifier.height(100.dp))
        }

    }

}

@Preview
@Composable
fun ProfileScreenPreview() {
    ThirdPJTheme {
        ProfileScreen()
    }
}