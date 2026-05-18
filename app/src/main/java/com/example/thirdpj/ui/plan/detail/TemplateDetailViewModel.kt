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

}