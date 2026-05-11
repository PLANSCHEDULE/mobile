package com.example.thirdpj.ui.create.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.thirdpj.ui.create.components.CreatePlanHeader
import com.example.thirdpj.ui.create.components.CreatePlanTopBar
import com.example.thirdpj.ui.theme.ThirdPJTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePlanScreen() {
    Scaffold(
        topBar = {
            CreatePlanTopBar()
        }

    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            CreatePlanHeader()
        }

    }

}

@Preview
@Composable
fun CreatePlanScreenPreview() {
    ThirdPJTheme {
        CreatePlanScreen()
    }
}