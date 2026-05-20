package com.example.thirdpj.ui.plan.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirdpj.R
import com.example.thirdpj.ui.testdata.PlanItem
import com.example.thirdpj.ui.theme.ThirdPJTheme
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun Plan(item: PlanItem,
         onCheckedChange: ((Boolean) -> Unit)?,
         onTitleChange: (String) -> Unit,
         onTimeClick: () -> Unit,
         onDeleteClick: () -> Unit,
         canMoveUp: Boolean,
         canMoveDown: Boolean,
         onMoveUp: () -> Unit,
         onMoveDown: () -> Unit,
         modifier: Modifier = Modifier,
) {

    val  timeFormatter = remember { DateTimeFormatter.ofPattern("HH:mm") }

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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = onMoveUp,
                    enabled = canMoveUp,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "위로 이동",
                        tint = if (canMoveUp) Color.Gray else Color.LightGray
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                IconButton(
                    onClick = onMoveDown,
                    enabled = canMoveDown,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "아래로 이동",
                        tint = if (canMoveDown) Color.Gray else Color.LightGray
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))

            // 시간
            Surface(
                onClick = onTimeClick,
                color = Color(0xFFEEECFB),
                shape = RoundedCornerShape(4.dp)
            ) {
                // 시간 선택을 어떻게 하면 좋을까, 선택? 시간 선택 컴포넌트를 따로 만들어야 하는 듯
                Text(
                    text = item.time.format(timeFormatter),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = if(item.isDone) Color.LightGray else Color(0xFF7F77DD)
                    ),
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 6.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // 체크박스
            // 생각해보니 생성 수정 페이지는 체크 박스가 눌리면 안됨
            Checkbox(
                checked = item.isDone,
                onCheckedChange = onCheckedChange,
                enabled = true,
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF7F77DD))
            )

            Spacer(modifier = Modifier.width(4.dp))

            // 글자 입력 영역
            OutlinedTextField(
                value = item.title,
                onValueChange = onTitleChange,
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = if(item.isDone) Color.LightGray else Color.Black
                ),
                modifier = Modifier.weight(1f),
                placeholder = {
                    Text(
                        text = "일정 제목 입력",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.LightGray
                        )
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedBorderColor = Color(0xFF7F77DD),
                    unfocusedBorderColor = Color(0xFFCECBF6),
                    disabledBorderColor = Color(0xFFCECBF6),
                    cursorColor = Color(0xFF7F77DD)
                ),
                singleLine = true
            )

            // 삭제 버튼
            IconButton(
                onClick = onDeleteClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "삭제",
                    tint = Color.Gray

                )
            }
        }

        // 삭제는 어떻게 구현해야 하나? 옆에 쓰레기통 아이콘 표시?
    }
}

@Preview
@Composable
fun PlanPreview() {
    ThirdPJTheme {
        val dummyData = PlanItem(
            id = 1,
            time = LocalTime.of(12, 0),
            title = "점심 먹기",
            isDone = false
        )
        Plan(
            item = dummyData,
            onCheckedChange = {},
            onTitleChange = {},
            onTimeClick = {},
            onDeleteClick = {},
            canMoveUp = true,
            canMoveDown = true,
            onMoveUp = {},
            onMoveDown = {}
        )
    }
}