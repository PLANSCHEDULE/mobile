package com.example.thirdpj.ui.global.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun MainAddButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color(0xFF534AB7),
        contentColor = Color.White,
        shape = CircleShape,
        modifier = Modifier.size(64.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "일정 추가",
            modifier = Modifier.size(28.dp)
        )
    }
}

@Preview
@Composable
fun MainAddButtonPreview() {
    ThirdPJTheme {
        MainAddButton(onClick = {})
    }
}