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

    private var currentPage = 0
    private var isLastPage = false

    fun fetchTemplates(isRefresh: Boolean = false) {
        if (_isLoading.value || (!isRefresh && isLastPage)) return

        if (isRefresh) {
            currentPage = 0
            isLastPage = false
        }

        viewModelScope.launch {
            _isLoading.value = true
            repository.getAllTemplates(page = currentPage, size = 10)
                .onSuccess { slice ->
                    _templates.value = if (isRefresh) slice.content
                    else _templates.value + slice.content
                    isLastPage = slice.last
                    if (!isLastPage) currentPage++
                }
                .onFailure { it.printStackTrace() }
            _isLoading.value = false
        }
    }
    private val _top10Templates = MutableStateFlow<List<PostTemplateDto>>(emptyList())
    val top10Templates: StateFlow<List<PostTemplateDto>> = _top10Templates.asStateFlow()

    fun fetchTop10() {
        viewModelScope.launch {
            repository.getTop10Templates()
                .onSuccess { _top10Templates.value = it }
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
                .onSuccess {
                    _templates.value = _templates.value.map { template ->
                        if (template.postTemplateId == postTemplateId) {
                            template.copy(
                                isFavorite = !template.isFavorite,
                                favoriteCount = if (template.isFavorite)
                                    template.favoriteCount - 1
                                else
                                    template.favoriteCount + 1
                            )
                        } else template
                    }
                }
                .onFailure { it.printStackTrace() }
        }
    }
}