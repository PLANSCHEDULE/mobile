package com.example.thirdpj.ui.plan.edit.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thirdpj.data.template.dto.TemplateResponse
import com.example.thirdpj.ui.plan.components.AddPlanItemButton
import com.example.thirdpj.ui.plan.components.Plan
import com.example.thirdpj.ui.plan.create.components.CreatePlanHeader
import com.example.thirdpj.ui.plan.create.components.CreatePlanTopBar
import com.example.thirdpj.ui.plan.create.screens.move
import com.example.thirdpj.ui.plan.edit.EditPlanViewModel
import com.example.thirdpj.ui.testdata.PlanItem
import kotlinx.coroutines.launch
import java.time.LocalTime

// ui/plan/edit/EditPlanScreen.kt
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPlanScreen(
    templateId: Long,
    initialTemplate: TemplateResponse,
    onBackClick: () -> Unit = {},
    viewModel: EditPlanViewModel = viewModel()
) {
    val context = LocalContext.current
    val planItems = viewModel.planItems

    LaunchedEffect(templateId) {
        viewModel.initWithTemplate(initialTemplate)
    }

    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<PlanItem?>(null) }
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CreatePlanTopBar(
                onBackClick = onBackClick,
                onSaveClick = {
                    viewModel.updateTemplate(
                        templateId = templateId,
                        onSuccess = {
                            Toast.makeText(context, "수정되었습니다.", Toast.LENGTH_SHORT).show()
                            onBackClick()
                        },
                        onError = { msg ->
                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                        }
                    )
                }
            )
        },
        containerColor = Color(0xFFF5F3FF)

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .imePadding()
        ) {
            CreatePlanHeader(
                title = viewModel.templateTitle,
                onTitleChange = { viewModel.onTitleChange(it) }
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                itemsIndexed(planItems, key = { _, item -> item.id }) { index, item ->
                    Plan(
                        item = item,
                        onCheckedChange = { isChecked ->
                            val idx = planItems.indexOfFirst { it.id == item.id }
                            if (idx != -1) planItems[idx] = item.copy(isDone = isChecked)
                        },
                        onTitleChange = { newTitle ->
                            val idx = planItems.indexOf(item)
                            if (idx != -1) planItems[idx] = item.copy(title = newTitle)
                        },
                        onTimeClick = {
                            selectedItem = item
                            showBottomSheet = true
                        },
                        onDeleteClick = { planItems.remove(item) },
                        canMoveUp = index > 0,
                        canMoveDown = index < planItems.lastIndex,
                        onMoveUp = { planItems.move(index, index - 1) },
                        onMoveDown = { planItems.move(index, index + 1) },
                        modifier = Modifier
                    )
                }

                item {
                    AddPlanItemButton(
                        onClick = {
                            val nextId = (planItems.maxOfOrNull { it.id } ?: 0) + 1
                            planItems.add(PlanItem(id = nextId, time = LocalTime.of(12, 0), title = "", isDone = false))
                            coroutineScope.launch {
                                lazyListState.animateScrollToItem(planItems.size)
                            }
                        }
                    )
                }
            }
        }

        if (showBottomSheet && selectedItem != null) {
            val timePickerState = rememberTimePickerState(
                initialHour = selectedItem!!.time.hour,
                initialMinute = selectedItem!!.time.minute,
                is24Hour = true
            )
            ModalBottomSheet(onDismissRequest = { showBottomSheet = false }) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp, top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("시간 선택", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                    TimePicker(state = timePickerState)
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = {
                            val newTime = LocalTime.of(timePickerState.hour, timePickerState.minute)
                            val idx = planItems.indexOfFirst { it.id == selectedItem?.id }
                            if (idx != -1) planItems[idx] = planItems[idx].copy(time = newTime)
                            showBottomSheet = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7F77DD)),
                        modifier = Modifier.fillMaxWidth(0.5f)
                    ) {
                        Text("선택 완료", fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}