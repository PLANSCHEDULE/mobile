package com.example.thirdpj.ui.create.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirdpj.ui.theme.ThirdPJTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePlanHeader(

) {

    var title by remember { mutableStateOf("") }

    val today = remember {
        // java.time 대신 java.util.Date
        // 지금 최소 버전은 24로 해놓았기 때문에 26이상에서 사용가능한 java.time보단 java.util.time을 사용
        val formatter = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        formatter.format(Date())
    }

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
        BasicTextField(
            value = title,
            onValueChange = {
                title = it
            },
            // 아래서 발생한 제목을 입력하세요와 커서 입력 차이를 textStyle로 지정해주면 해결됨!
            textStyle = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            decorationBox = {innerTextField ->
                // 제목이 없는지 확인한 후에 제목 입력하세요 띄우기
                if(title.isEmpty()) {
                    Text(
                        text = "제목을 입력하세요",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.LightGray
                    )
                    // 제목을 입력하세요는 큰데 커서는 또 작음.. textStyle 지정으로 해결
                }
                // 실제 입력되는 글자는 여기서!
                innerTextField()

            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 작성 날짜
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color(0xFFF8F0E5), RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 4.dp)

        ) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Color(0xFFF27A54)
            )
            Text(
                text = "$today",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }

    }

}


@Preview
@Composable
fun CreatePlanHeaderPreview() {
    ThirdPJTheme {
        CreatePlanHeader()
    }
}