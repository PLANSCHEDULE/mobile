package com.example.thirdpj.ui.plan.create.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirdpj.ui.data.PlanItem
import com.example.thirdpj.ui.plan.components.Plan
import com.example.thirdpj.ui.plan.create.components.CreatePlanHeader
import com.example.thirdpj.ui.plan.create.components.CreatePlanTopBar
import com.example.thirdpj.ui.theme.ThirdPJTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePlanScreen(
    // 가짜 데이터
    initalItems: List<PlanItem> = emptyList()
) {

    val planItems = remember { mutableStateListOf<PlanItem>().apply { addAll(initalItems) } }

    // bottomsheet 상태 확인
    var showBottomSheet by remember { mutableStateOf(false) }

    // 현재 수정 중인 plan(row) 확인
    var selectedItem by remember { mutableStateOf<PlanItem?>(null) }

    Scaffold(
        topBar = {
            CreatePlanTopBar()
        }

    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()

        ) {
            // 헤더 고정 안됨 -> header을 lazycolumn안에 둬서 고정이 되지 않았음
            CreatePlanHeader()

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                // 헤더 영역


                // 일정 리스트 영역
                // 이거 위치 이동 어떻게 할지 방식을 정해야됨.
                items(planItems) {item ->
                    Plan(
                        item = item,
                        onTimeClick = {
                            selectedItem = item
                            showBottomSheet = true
                        },
                        onCheckedChange = {isChecked ->
                            val index = planItems.indexOf(item)
                            if(index != -1) {
                                planItems[index] = item.copy(isDone = isChecked)
                            }
                        },
                        onDeleteClick = {
                            planItems.remove(item)
                        }
                    )
                }
            }
        }

    }

}

@Preview
@Composable
fun CreatePlanScreenPreview() {
    ThirdPJTheme {
        val mockData = listOf(
            PlanItem(id = 1, time = "10:00", title = "제주 공항 도착", isDone = false),
            PlanItem(id = 2, time = "12:00", title = "렌터카 픽업 및 점심 식사 (흑돼지)", isDone = false),
            PlanItem(id = 3, time = "15:00", title = "함덕 해수욕장 산책", isDone = true)
        )
        CreatePlanScreen(initalItems = mockData)
    }
}