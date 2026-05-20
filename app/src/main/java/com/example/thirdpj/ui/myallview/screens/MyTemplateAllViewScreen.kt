package com.example.thirdpj.ui.myallview.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirdpj.data.template.dto.TemplateResponse
import com.example.thirdpj.ui.allview.components.AllViewTopBar
import com.example.thirdpj.ui.global.components.MyTemplateCard

@Composable
fun MyTemplateAllViewScreen(
    title: String,
    templates: List<TemplateResponse>,
    onBackClick: () -> Unit,
    onCardClick: (Long) -> Unit = {}
) {
    Scaffold(
        topBar = {
            AllViewTopBar(
                title = title,
                onBackClick = onBackClick
            )
        },
        containerColor = Color(0xFFF5F3FF)
    ) { innerPadding ->
        if (templates.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "아직 템플릿이 없습니다.",
                    color = Color(0xFFAFA9EC),
                    fontSize = 14.sp
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(templates, key = { it.id }) { template ->
                    MyTemplateCard(
                        template = template,
                        onClick = { onCardClick(template.id) }
                    )
                }
            }
        }
    }
}