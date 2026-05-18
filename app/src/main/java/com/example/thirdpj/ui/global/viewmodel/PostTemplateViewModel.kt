package com.example.thirdpj.ui.global.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thirdpj.data.api.RetrofitClient
import com.example.thirdpj.data.post.repository.PostTemplateRepository
import kotlinx.coroutines.launch

class PostTemplateViewModel : ViewModel() {
    private val repository = PostTemplateRepository(RetrofitClient.postTemplateApiService)

    // 다운로드
    fun downloadTemplate(
        postTemplateId: Long,
        onSuccess: () -> Unit = {}
    ) {
        viewModelScope.launch {
            repository.downloadTemplate(postTemplateId)
                .onSuccess { onSuccess() }
                .onFailure { it.printStackTrace() }
        }
    }

    // 찜 토글
    fun toggleFavorite(
        postTemplateId: Long,
        currentIsFavorite: Boolean,
        onSuccess: (newIsFavorite: Boolean) -> Unit = {}
    ) {
        viewModelScope.launch {
            repository.toggleFavorite(postTemplateId)
                .onSuccess { onSuccess(!currentIsFavorite) }
                .onFailure { it.printStackTrace() }
        }
    }
}