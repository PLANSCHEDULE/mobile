package com.example.thirdpj.ui.plan.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
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
import androidx.compose.ui.unit.dp

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

    // android에서 ModalBottomSheet를 통해 bottomsheet 구현 가능
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
                    selectorColor = Color(0xFFF27A54),
                    periodSelectorSelectedContentColor = Color(0xFFFFF7F0)
                )
            )

        }
    }

}