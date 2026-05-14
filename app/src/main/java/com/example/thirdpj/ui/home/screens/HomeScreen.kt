package com.example.thirdpj.ui.home.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
        // 하단 바 수정하면서 navigate 설정해 주면서 오류남
        // MainActivity에서 구현함으로써 homescreen에서는 삭제
        // 근데 mainActivity에서 설정을 해두었음
        containerColor = Color.White
    ) {innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 15.dp)
        ) {
            item(span = { GridItemSpan(2)}) {
               Column{
                   Spacer(modifier = Modifier.height(16.dp))

                   HomeBanner()

                   Spacer(modifier = Modifier.height(24.dp))

                   Text(
                       text = "인기 템플릿",
                       fontWeight = FontWeight.Bold,
                       fontSize = 18.sp
                   )
                   Spacer(modifier = Modifier.height(16.dp))

               }
            }

            items(10) {
                TemplateCard(
                    modifier = Modifier.fillMaxWidth()
                )
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