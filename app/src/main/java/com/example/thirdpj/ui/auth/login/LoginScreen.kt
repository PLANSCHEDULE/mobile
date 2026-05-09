package com.example.thirdpj.ui.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
fun LoginScreen() {
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
        ) {

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