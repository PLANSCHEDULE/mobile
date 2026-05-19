package com.example.thirdpj.ui.plan.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thirdpj.data.api.RetrofitClient
import com.example.thirdpj.data.template.dto.TemplateResponse
import com.example.thirdpj.data.template.dto.TemplateUpdateItem
import com.example.thirdpj.data.template.dto.TemplateUpdateRequest
import com.example.thirdpj.data.template.repository.TemplateRepository
import com.example.thirdpj.ui.testdata.PlanItem
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class EditPlanViewModel : ViewModel() {
    private val repository = TemplateRepository(RetrofitClient.templateApiService)

    var templateTitle by mutableStateOf("")
        private set

    var targetDate by mutableStateOf("")
        private set

    val planItems = mutableStateListOf<PlanItem>()

    fun initWithTemplate(template: TemplateResponse) {
        templateTitle = template.title
        targetDate = template.targetDate
        planItems.clear()
        planItems.addAll(
            template.items.sortedBy { it.sequence }.mapIndexed { index, item ->
                PlanItem(
                    id = index + 1,
                    time = LocalTime.parse(item.itemTime, DateTimeFormatter.ofPattern("HH:mm")),
                    title = item.content,
                    isDone = item.isCompleted
                )
            }
        )
    }

    fun onTitleChange(newTitle: String) {
        templateTitle = newTitle
    }

    fun updateTemplate(
        templateId: Long,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (templateTitle.isBlank()) {
            onError("제목을 입력해 주세요.")
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

                val items = planItems.mapIndexed { index, item ->
                    TemplateUpdateItem(
                        time = item.time.format(timeFormatter),
                        content = item.title.ifBlank { "이름 없는 일정" },
                        sequence = index + 1,
                        isCompleted = item.isDone,
                        isAlarmOn = false
                    )
                }

                val request = TemplateUpdateRequest(
                    title = templateTitle,
                    targetDate = LocalDate.now().format(dateFormatter),
                    items = items
                )

                repository.updateTemplate(templateId, request)
                    .onSuccess { onSuccess() }
                    .onFailure { onError(it.localizedMessage ?: "수정 중 오류가 발생했습니다.") }

            } catch (e: Exception) {
                onError("인터넷 연결 상태를 확인해 주세요.")
            }
        }
    }
}