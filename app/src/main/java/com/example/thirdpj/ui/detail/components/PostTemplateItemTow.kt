package com.example.thirdpj.ui.detail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PostTemplateItemRow(
    itemTime: String,
    content: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = itemTime,
            fontSize = 14.sp,
            color = Color(0xFFEF7F61),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(50.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            color = Color(0xFFF9F9F9),
            shadowElevation = 1.dp
        ) {
            Text(
                text = content,
                fontSize = 14.sp,
                modifier = Modifier.padding(12.dp),
                fontWeight = FontWeight.Medium
            )
        }
    }
}