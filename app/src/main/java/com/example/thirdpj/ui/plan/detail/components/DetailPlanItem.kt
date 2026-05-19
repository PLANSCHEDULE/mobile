package com.example.thirdpj.ui.plan.detail.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DetailPlanItem(
    itemTime: String,
    content: String,
    isCompleted: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        color = Color.White,
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 시간
            Surface(
                color = Color(0xFFEEECFB),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = itemTime,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (isCompleted) Color.LightGray else Color(0xFF7F77DD)
                    ),
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // 체크박스 (읽기 전용)
            Checkbox(
                checked = isCompleted,
                onCheckedChange = null,
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF7F77DD))
            )

            Spacer(modifier = Modifier.width(8.dp))

            // 내용 (읽기 전용)
            Text(
                text = content,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = if (isCompleted) Color.LightGray else Color(0xFF534AB7)
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}