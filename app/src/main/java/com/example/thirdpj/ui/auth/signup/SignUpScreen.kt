package com.example.thirdpj.ui.auth.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.thirdpj.ui.theme.ThirdPJTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen() {
    // 상단바, Scaffold
    Scaffold(
        // 화면 상단바를 만들기 위해서 Scaffold의 Topbar 사용
        topBar = {
            // 회원가입 페이지니까 우선 제목 가운데로 정렬
            // 위에 OptIn 안써주면 아래 내용 에러남
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "회원가입")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "뒤로가기")
                    }
                },
                // 상단바 구분이 안가서 우선 색상 아무거나
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Yellow
                )
            )
        }
    ) {innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){
            Text("상단바 틀 잡기")
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    ThirdPJTheme{
        SignUpScreen()
    }
}