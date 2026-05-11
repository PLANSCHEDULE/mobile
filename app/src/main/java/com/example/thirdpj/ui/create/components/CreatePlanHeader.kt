package com.example.thirdpj.ui.create.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirdpj.ui.theme.ThirdPJTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePlanHeader(

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFFBF5))
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        // 상태 표시
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("✏️ ", fontSize = 12.sp)
            Text(
                text = "새로운 일정 작성",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 제목 입력창



        // 작성 날짜



    }

}


@Preview
@Composable
fun CreatePlanHeaderPreview() {
    ThirdPJTheme {
        CreatePlanHeader()
    }
}