package com.example.thirdpj.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thirdpj.data.api.RetrofitClient
import com.example.thirdpj.data.post.dto.PostTemplateDto
import com.example.thirdpj.data.post.repository.PostTemplateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val repository = PostTemplateRepository(RetrofitClient.postTemplateApiService)

    private val _results = MutableStateFlow<List<PostTemplateDto>>(emptyList())
    val results: StateFlow<List<PostTemplateDto>> = _results.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    var keyword by mutableStateOf("")
        private set

    private var currentPage = 0
    private var isLastPage = false

    fun onKeywordChange(newKeyword: String) {
        keyword = newKeyword
    }

    fun search(isRefresh: Boolean = false) {
        if (keyword.isBlank()) {
            _results.value = emptyList()
            return
        }
        if (_isLoading.value || (!isRefresh && isLastPage)) return

        if (isRefresh) {
            currentPage = 0
            isLastPage = false
        }

        viewModelScope.launch {
            _isLoading.value = true
            repository.searchTemplates(keyword = keyword, page = currentPage, size = 10)
                .onSuccess { slice ->
                    _results.value = if (isRefresh) slice.content
                    else _results.value + slice.content
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
                    _results.value = _results.value.map { template ->
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