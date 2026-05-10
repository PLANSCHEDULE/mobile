package com.example.thirdpj.ui.home.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirdpj.ui.components.BottomBar
import com.example.thirdpj.ui.home.components.HomeBanner
import com.example.thirdpj.ui.home.components.HomeHeader
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            HomeHeader()
        },

        bottomBar = {
            BottomBar()
        },

        containerColor = Color.White
    ) {innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(horizontal = 15.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            HomeBanner()

        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    ThirdPJTheme {
        HomeScreen()
    }
}