package com.example.thirdpj.ui.home.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirdpj.ui.global.components.BottomBar
import com.example.thirdpj.ui.global.components.TemplateCard
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

            // 배너 영역
            HomeBanner()

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "인기 템플릿",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 카드 영역
            //lazycolumn?
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(10){
                    TemplateCard()
                }
                //말줄임표
                item(span = { GridItemSpan(2)}) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

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