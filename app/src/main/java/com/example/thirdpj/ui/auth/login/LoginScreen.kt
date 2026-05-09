package com.example.thirdpj.ui.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirdpj.ui.theme.ThirdPJTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    var isEmailLoginVisible by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("로그인") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Yellow
                )
            )
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            // 우선 oauth도 추가될 수 있으니 이메일로 로그인 버튼 따로 만들기
            if(!isEmailLoginVisible) {
                // 로그인 화면에 처음 들어갔을 때 보이는 로그인 버튼
                Button(
                    onClick = {isEmailLoginVisible = true},
                    // 버튼이 너무 양 옆일 때 해결 방법: Column에 padding 적절히 주기. 안 주면 너무 양옆에 딱 붙어버림
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("이메일로 로그인하기")
                }
            } else {
                // 이메일로 로그인 버튼을 누르면 나오는 기능 구현 필요

            }
        }

    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    ThirdPJTheme{
        LoginScreen()
    }
}