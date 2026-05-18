package com.example.thirdpj.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thirdpj.data.api.RetrofitClient
import com.example.thirdpj.data.post.dto.PostTemplateDto
import com.example.thirdpj.data.post.repository.PostTemplateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel : ViewModel() {
    private val repository = PostTemplateRepository(RetrofitClient.postTemplateApiService)

    private val _templates = MutableStateFlow<List<PostTemplateDto>>(emptyList())
    val templates: StateFlow<List<PostTemplateDto>> = _templates.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var currentPage = 0
    private var isLastPage = false

    fun fetchMyFavorites(isRefresh: Boolean = false) {
        if (_isLoading.value || (!isRefresh && isLastPage)) return

        if (isRefresh) {
            currentPage = 0
            isLastPage = false
        }

        viewModelScope.launch {
            _isLoading.value = true
            repository.getMyFavorites(page = currentPage, size = 10)
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
                    _templates.value = _templates.value.filter {
                        it.postTemplateId != postTemplateId
                    }
                }
                .onFailure { it.printStackTrace() }
        }
    }
}