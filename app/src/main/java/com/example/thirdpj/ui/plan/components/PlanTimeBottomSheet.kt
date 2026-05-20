package com.example.thirdpj.ui.plan.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanTimeBottomSheet(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit

) {
    // 열림과 닫힘 제어
    val sheetState = rememberModalBottomSheetState()
    // 시간 선택
    val timePickerState = rememberTimePickerState(is24Hour = true)

    // android에서 ModalBottomSheet를 통해 bottom sheet 구현 가능
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        // 열림/닫힘
        sheetState = sheetState,
        containerColor = Color.White,
        dragHandle = { BottomSheetDefaults.DragHandle(color = Color.LightGray)}

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp, top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "시간 선택",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 시간 선택기
            TimePicker(
                state = timePickerState,
                colors = TimePickerDefaults.colors(
                    // 선택 시에 색상 선택 가능
                    selectorColor = Color(0xFF7F77DD),
                    periodSelectorSelectedContentColor = Color(0xFFF5F3FF)
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // 확인 버튼 추가
            Button(
                onClick = {
                    //warning 해결: Locale.US를 사용하면 어떤 국가는 무조건 숫자로 사용 가능하게 명시
                    val formattedTime = String.format(Locale.US,"%02d:%02d", timePickerState.hour, timePickerState.minute)
                    onConfirm(formattedTime)
                },
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7F77DD))
            ) {
                Text("시간 적용하기", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

        }
    }

}