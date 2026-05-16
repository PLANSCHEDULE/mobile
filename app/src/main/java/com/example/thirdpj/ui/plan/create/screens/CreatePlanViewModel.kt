package com.example.thirdpj.ui.plan.create.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thirdpj.data.template.api.TemplateApiService
import com.example.thirdpj.data.template.dto.TemplateCreateRequest
import com.example.thirdpj.data.template.dto.TemplateItemDto
import com.example.thirdpj.ui.testdata.PlanItem
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class CreatePlanViewModel(
    private val apiService: TemplateApiService
) : ViewModel() {

    var templateTitle by mutableStateOf("")
        private set

    fun onTitleChange(newTitle: String) {
        templateTitle = newTitle
    }

    val planItems = mutableStateListOf<PlanItem>()

    fun saveTemplateToServer(onSuccess: () -> Unit, onError: (String) -> Unit) {

        if (templateTitle.isBlank()) {
            onError("일정 제목을 입력해 주세요.")
            return
        }

        if (planItems.isEmpty()) {
            onError("최소 한 개 이상의 일정을 추가해 주세요.")
            return
        }

        viewModelScope.launch {
            try {
                val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
                val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

                val itemDtos = planItems.mapIndexed { index, item ->
                    TemplateItemDto(
                        time = item.time.format(timeFormatter),
                        content = item.title.ifBlank { "이름 없는 일정" },
                        sequence = index + 1,
                        isAlarmOn = false
                    )
                }

                val requestBody = TemplateCreateRequest(
                    title = templateTitle,
                    background = "DEFAULT_BG",
                    targetDate = LocalDate.now().format(dateFormatter),
                    items = itemDtos
                )

                val response = apiService.createTemplate(requestBody)

                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null && apiResponse.status == 200) {
                        onSuccess()
                    } else {
                        onError(apiResponse?.message ?: "서버 오류가 발생했습니다.")
                    }
                } else {
                    onError("네트워크 요청 실패 (코드: ${response.code()})")
                }
            } catch (e: Exception) {
                onError("인터넷 연결을 확인해 주세요.")
            }
        }
    }

}