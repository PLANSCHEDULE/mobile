package com.example.thirdpj.ui.plan.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thirdpj.data.api.RetrofitClient
import com.example.thirdpj.data.template.dto.TemplateResponse
import com.example.thirdpj.data.template.repository.TemplateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TemplateDetailViewModel : ViewModel() {
    private val repository = TemplateRepository(RetrofitClient.templateApiService)

    private val _template = MutableStateFlow<TemplateResponse?>(null)
    val template: StateFlow<TemplateResponse?> = _template.asStateFlow()

    fun fetchTemplateDetail(templateId: Long) {
        viewModelScope.launch {
            repository.getTemplateDetail(templateId)
                .onSuccess { _template.value = it }
                .onFailure { it.printStackTrace() }
        }
    }
    // 템플릿 삭제
    fun deleteTemplate(templateId: Long, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            repository.deleteTemplate(templateId)
                .onSuccess { onSuccess() }
                .onFailure { onError(it.message ?: "삭제 중 오류가 발생했습니다.") }
        }
    }

    fun shareTemplate(templateId: Long, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            repository.shareTemplate(templateId)
                .onSuccess { onSuccess() }
                .onFailure { onError(it.message ?: "공유 중 오류가 발생했습니다.") }
        }
    }

}