package com.example.thirdpj.ui.create.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.thirdpj.ui.data.PlanItem
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun CreatePlanList(item: PlanItem, onCheckedChange: (Boolean) -> Unit) {

}

@Preview
@Composable
fun CreatePlanListPreview(
) {
    ThirdPJTheme {
        val dummyData = PlanItem(
            id = 1,
            time = "12:00",
            title = "점심 먹기",
            emoji = "🐷",
            isDone = false
        )
       CreatePlanList(
           item = dummyData,
           onCheckedChange = {}
       )
    }
}