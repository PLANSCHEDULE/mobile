package com.example.thirdpj.ui.detail.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirdpj.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostTemplateDetailTopBar(
    onBackClick: () -> Unit = {}
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFF5F3FF)),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "뒤로가기",
                    tint = Color(0xFF534AB7)
                )
            }
        },
        title = {
            Text(
                text = "일정 상세보기",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color(0xFF534AB7)
            )
        }
    )
}