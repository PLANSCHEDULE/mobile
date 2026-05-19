package com.example.thirdpj.ui.plan.create.screens


import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.thirdpj.data.api.RetrofitClient
import com.example.thirdpj.data.template.repository.TemplateRepository
import com.example.thirdpj.ui.plan.components.AddPlanItemButton
import com.example.thirdpj.ui.testdata.PlanItem
import com.example.thirdpj.ui.plan.components.Plan
import com.example.thirdpj.ui.plan.create.components.CreatePlanHeader
import com.example.thirdpj.ui.plan.create.components.CreatePlanTopBar
import com.example.thirdpj.ui.theme.ThirdPJTheme
import kotlinx.coroutines.launch
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePlanScreen(
    viewModel: CreatePlanViewModel = viewModel(
        factory = viewModelFactory {
           initializer {
                CreatePlanViewModel(
                    repository = TemplateRepository(RetrofitClient.templateApiService)
                )
            }


        }
    ),
    onBackClick: () -> Unit = {}
) {

    val context = LocalContext.current
    val planItems = viewModel.planItems

    // bottomsheet 상태 확인
    var showBottomSheet by remember { mutableStateOf(false) }

    // 현재 수정 중인 plan(row) 확인
    var selectedItem by remember { mutableStateOf<PlanItem?>(null) }

    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CreatePlanTopBar(
                onBackClick =  onBackClick,
                onSaveClick = {
                    viewModel.saveTemplateToServer(
                        onSuccess = {
                            Toast.makeText(context, "성공적으로 저장되었습니다.", Toast.LENGTH_SHORT).show()
                        },
                        onError = {errorMessage ->
                            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                        }
                    )
                },
                onShareClick = {
                    viewModel.shareTemplate(
                        onSuccess = {
                            Toast.makeText(context, "공유되었습니다!", Toast.LENGTH_SHORT).show()
                        },
                        onError = { msg ->
                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                        }
                    )
                }



            )
        },
        containerColor = Color(0xFFF5F3FF)

    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .imePadding()

        ) {
            // 헤더 고정 안됨 -> header을 lazycolumn안에 둬서 고정이 되지 않았음
            CreatePlanHeader(
                title = viewModel.templateTitle,
                onTitleChange = {viewModel.onTitleChange(it)}
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                // 헤더 영역


                // 일정 리스트 영역
                // 이거 위치 이동 어떻게 할지 방식을 정해야됨.
                itemsIndexed(
                    planItems, key = {_, item -> item.id}

                ) {index, item ->
                    Plan(
                        item = item,
                        onCheckedChange = {
                            isChecked ->
                        //    val idx = planItems.indexOf(item) 시간 반영이 안됨.
                            val idx = planItems.indexOfFirst { it.id == item.id }
                            if (idx != -1) {
                                planItems[idx] = item.copy(isDone = isChecked)
                            }
                        },
                        onTitleChange = {newTitle ->
                            val idx = planItems.indexOf(item)
                            if(idx != -1) {
                                planItems[idx] = item.copy(title = newTitle)
                            }
                        },
                        onTimeClick = {
                            selectedItem = item
                            showBottomSheet = true
                        },
                        onDeleteClick = {
                            planItems.remove(item)
                        },
                        canMoveUp = index > 0,
                        canMoveDown = index < planItems.lastIndex,
                        onMoveUp = {
                            planItems.move(index, index - 1)
                        },
                        onMoveDown = {
                            planItems.move(index, index + 1)
                        },
                        modifier = Modifier
                    )
                }

                item {
                    AddPlanItemButton(
                        onClick = {
                            val nextId = (planItems.maxOfOrNull { it.id } ?: 0) + 1

                            planItems.add(
                                PlanItem(id = nextId, time = LocalTime.of(12, 0), title = "", isDone = false)
                            )

                            coroutineScope.launch {
                                lazyListState.animateScrollToItem(planItems.size)
                            }


                        }
                    )
                }

            }
        }

        if(showBottomSheet && selectedItem != null) {
            // 현재 아이템이 가진 시/분을 바탕으로 타임피커 초기 상태 생성
            val timePickerState = rememberTimePickerState(
                initialHour = selectedItem!!.time.hour,
                initialMinute = selectedItem!!.time.minute,
                is24Hour = true // 24시간 포맷 사용
            )

            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp, top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "시간 선택",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // 💡 구글 안드로이드 공식 컴포즈 시간 선택기
                    TimePicker(state = timePickerState)

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            // 피커에서 선택한 시/분으로 새로운 LocalTime 객체 생성
                            val newLocalTime = LocalTime.of(timePickerState.hour, timePickerState.minute)

                            // 리스트에서 해당 아이템을 찾아 시간 갱신
                           // val idx = planItems.indexOf(selectedItem)
                            val idx = planItems.indexOfFirst { it.id == selectedItem?.id }
                            if (idx != -1) {
                                planItems[idx] = planItems[idx].copy(time = newLocalTime)
                            }
                            showBottomSheet = false // 시트 닫기
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF27A54)),
                        modifier = Modifier.fillMaxWidth(0.5f)
                    ) {
                        Text("선택 완료", fontWeight = FontWeight.Bold)
                    }
                }
            }

        }

    }

}

//@Preview
//@Composable
//fun CreatePlanScreenPreview() {
//    ThirdPJTheme {
//        val mockData = listOf(
//            PlanItem(id = 1, time = LocalTime.of(10, 0), title = "제주 공항 도착", isDone = false),
//            PlanItem(id = 2, time = LocalTime.of(12, 30), title = "렌터카 픽업 및 점심 식사 (흑돼지)", isDone = false),
//            PlanItem(id = 3, time = LocalTime.of(15, 0), title = "함덕 해수욕장 산책", isDone = true)
//        )
//        CreatePlanScreen(initalItems = mockData)
//    }
//}

fun <T> MutableList<T>.move(fromIndex: Int, toIntex: Int) {
    if(fromIndex == toIntex || fromIndex !in indices || toIntex !in indices)
        return
    val item = removeAt(fromIndex)
    add(toIntex, item)
}