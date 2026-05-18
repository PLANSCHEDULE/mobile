package com.example.thirdpj.ui.home.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thirdpj.data.api.RetrofitClient
import com.example.thirdpj.data.post.dto.PostTemplateDto
import com.example.thirdpj.data.post.repository.PostTemplateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = PostTemplateRepository(RetrofitClient.postTemplateApiService)

    private val _templates = MutableStateFlow<List<PostTemplateDto>>(emptyList())
    val templates: StateFlow<List<PostTemplateDto>> = _templates.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun fetchTemplates() {
        viewModelScope.launch {
            _isLoading.value = true

            val result = repository.getAllTemplates(page = 0, size = 10)

            result.onSuccess { sliceResponse ->
                _templates.value = sliceResponse.content
            }.onFailure { exception ->
                exception.printStackTrace()
            }

            _isLoading.value = false
        }
    }
}