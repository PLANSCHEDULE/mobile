package com.example.thirdpj.ui.plan.create.screens


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirdpj.ui.data.PlanItem
import com.example.thirdpj.ui.plan.create.components.CreatePlanHeader
import com.example.thirdpj.ui.plan.create.components.CreatePlanTopBar
import com.example.thirdpj.ui.theme.ThirdPJTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePlanScreen() {

    val planItems = remember { mutableStateListOf<PlanItem>() }
    // bottomsheet 상태 확인
    val showBottomSheet by remember { mutableStateOf(false) }
    // 현재 수정 중인 plan(row) 확인
    val selectedItem by remember { mutableStateOf<PlanItem?>(null) }

    Scaffold(
        topBar = {
            CreatePlanTopBar()
        }

    ) {innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            // 헤더 영역
            item {
                CreatePlanHeader()
                Spacer(modifier = Modifier.height(8.dp))
            }

            // 일정 리스트 영역


        }

    }

}

@Preview
@Composable
fun CreatePlanScreenPreview() {
    ThirdPJTheme {
        CreatePlanScreen()
    }
}