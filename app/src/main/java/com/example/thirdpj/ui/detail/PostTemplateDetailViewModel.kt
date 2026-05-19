package com.example.thirdpj.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thirdpj.data.api.RetrofitClient
import com.example.thirdpj.data.post.dto.PostTemplateDto
import com.example.thirdpj.data.post.repository.PostTemplateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostTemplateDetailViewModel : ViewModel() {
    private val repository = PostTemplateRepository(RetrofitClient.postTemplateApiService)

    private val _template = MutableStateFlow<PostTemplateDto?>(null)
    val template: StateFlow<PostTemplateDto?> = _template.asStateFlow()

    fun fetchDetail(postTemplateId: Long) {
        viewModelScope.launch {
            repository.getPostTemplateDetail(postTemplateId)
                .onSuccess { _template.value = it }
                .onFailure { it.printStackTrace() }
        }
    }

    fun toggleFavorite(postTemplateId: Long) {
        viewModelScope.launch {
            repository.toggleFavorite(postTemplateId)
                .onSuccess {
                    _template.value = _template.value?.let {
                        it.copy(
                            isFavorite = !it.isFavorite,
                            favoriteCount = if (it.isFavorite) it.favoriteCount - 1 else it.favoriteCount + 1
                        )
                    }
                }
                .onFailure { it.printStackTrace() }
        }
    }

    fun downloadTemplate(postTemplateId: Long, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            repository.downloadTemplate(postTemplateId)
                .onSuccess { onSuccess() }
                .onFailure { onError(it.message ?: "다운로드 중 오류가 발생했습니다.") }
        }
    }
}