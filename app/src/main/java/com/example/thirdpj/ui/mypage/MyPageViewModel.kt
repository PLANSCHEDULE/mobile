package com.example.thirdpj.ui.mypage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thirdpj.data.api.RetrofitClient
import com.example.thirdpj.data.template.dto.TemplateResponse
import com.example.thirdpj.data.template.repository.TemplateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {
    private val repository = TemplateRepository(RetrofitClient.templateApiService)

    private val _myTemplates = MutableStateFlow<List<TemplateResponse>>(emptyList())
    val myTemplates: StateFlow<List<TemplateResponse>> = _myTemplates.asStateFlow()

    private var currentPage = 0
    private var isLastPage = false

    fun fetchMyTemplates(isRefresh: Boolean = false) {
        if (isLastPage && !isRefresh) return

        if (isRefresh) {
            currentPage = 0
            isLastPage = false
        }

        viewModelScope.launch {
            repository.getMyAllTemplates(page = currentPage, size = 10)
                .onSuccess { slice ->
                    _myTemplates.value = if (isRefresh) slice.content
                    else _myTemplates.value + slice.content
                    isLastPage = slice.last
                    if (!isLastPage) currentPage++
                }
                .onFailure { it.printStackTrace() }
        }
    }
}