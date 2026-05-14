package com.example.thirdpj.ui.allview.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.thirdpj.R
import com.example.thirdpj.ui.theme.ThirdPJTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllViewTopBar(
    title: String,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "뒤로가기")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )

}

@Preview
@Composable
fun AllViewTopBarPreview() {
    ThirdPJTheme {
        AllViewTopBar(
            title = "템플릿 전체 보기",
            onBackClick = {}
        )
    }
}