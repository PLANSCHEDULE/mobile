package com.example.thirdpj.ui.allview.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thirdpj.data.api.RetrofitClient
import com.example.thirdpj.data.post.dto.PostTemplateDto
import com.example.thirdpj.data.post.repository.PostTemplateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AllViewViewModel : ViewModel() {
    private val repository = PostTemplateRepository(RetrofitClient.postTemplateApiService)

    private val _templates = MutableStateFlow<List<PostTemplateDto>>(emptyList())
    val templates: StateFlow<List<PostTemplateDto>> = _templates.asStateFlow()

    fun fetchTop10() {
        viewModelScope.launch {
            repository.getTop10Templates()
                .onSuccess { _templates.value = it }
                .onFailure { it.printStackTrace() }
        }
    }

    fun downloadTemplate(postTemplateId: Long) {
        viewModelScope.launch {
            repository.downloadTemplate(postTemplateId)
                .onFailure { it.printStackTrace() }
        }
    }

    fun toggleFavorite(postTemplateId: Long) {
        viewModelScope.launch {
            repository.toggleFavorite(postTemplateId)
                .onFailure { it.printStackTrace() }
        }
    }
}