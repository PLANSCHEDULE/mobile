package com.example.thirdpj.ui.plan.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirdpj.R
import com.example.thirdpj.ui.data.PlanItem
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun Plan(item: PlanItem, onCheckedChange: (Boolean) -> Unit) {
    Surface(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        color = Color.Companion.White,
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier.Companion
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Companion.CenterVertically
        ) {
            // 드래그 아이콘 표시
            Icon(
                painter = painterResource(id = R.drawable.drag_indicator_icon),
                contentDescription = null,
                tint = Color.Companion.Gray
            )
        }

        // 삭제는 어떻게 구현해야 하나? 옆에 쓰레기통 아이콘 표시?
    }
}

@Preview
@Composable
fun PlanPreview(
) {
    ThirdPJTheme {
        val dummyData = PlanItem(
            id = 1,
            time = "12:00",
            title = "점심 먹기",
            emoji = "🐷",
            isDone = false
        )
        Plan(
            item = dummyData,
            onCheckedChange = {}
        )
    }
}