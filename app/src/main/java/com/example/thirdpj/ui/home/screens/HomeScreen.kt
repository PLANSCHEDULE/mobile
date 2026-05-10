package com.example.thirdpj.ui.home.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.thirdpj.ui.components.BottomBar
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
        Column(modifier = Modifier.padding(innerPadding)) {

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