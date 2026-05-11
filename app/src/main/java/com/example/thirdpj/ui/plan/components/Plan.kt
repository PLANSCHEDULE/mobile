package com.example.thirdpj.ui.plan.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirdpj.R
import com.example.thirdpj.ui.data.PlanItem
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun Plan(item: PlanItem,
         onCheckedChange: (Boolean) -> Unit,
         onTimeClick: () -> Unit
) {
    Surface(
        modifier = Modifier.Companion
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        color = Color.White,
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
                tint = Color.Companion.Gray,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            // 시간
            Surface(
                onClick = onTimeClick,
                color = Color.Transparent,
                shape = RoundedCornerShape(4.dp)
            ) {
                // 시간 선택을 어떻게 하면 좋을까, 선택? 시간 선택 컴포넌트를 따로 만들어야 하는 듯
                Text(
                    text = item.time,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = if(item.isDone) Color.LightGray else Color(0xFFF27A54)
                    ),
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 6.dp)
                )
            }
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
            onCheckedChange = {},
            onTimeClick = {}
        )
    }
}