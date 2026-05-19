package com.example.thirdpj.ui.mypage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thirdpj.data.api.RetrofitClient
import com.example.thirdpj.data.repository.AuthRepository
import com.example.thirdpj.data.template.dto.TemplateResponse
import com.example.thirdpj.data.template.repository.TemplateRepository
import com.example.thirdpj.util.TokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MyPageViewModel(
    private val tokenManager: TokenManager
) : ViewModel() {
    private val repository = TemplateRepository(RetrofitClient.templateApiService)
    private val authRepository = AuthRepository(RetrofitClient.authService)
    private val _myTemplates = MutableStateFlow<List<TemplateResponse>>(emptyList())
    val myTemplates: StateFlow<List<TemplateResponse>> = _myTemplates.asStateFlow()

    private var currentPage = 0
    private var isLastPage = false

    private val _downloadedTemplates = MutableStateFlow<List<TemplateResponse>>(emptyList())
    val downloadedTemplates: StateFlow<List<TemplateResponse>> = _downloadedTemplates.asStateFlow()

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

    fun fetchDownloadedTemplates(isRefresh: Boolean = false) {
        viewModelScope.launch {
            repository.getMyDownloadedTemplates(page = 0, size = 10)
                .onSuccess { slice -> _downloadedTemplates.value = slice.content }
                .onFailure { it.printStackTrace() }
        }
    }

    fun logout(onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                // ✅ collect → first()로 변경 (한 번만 가져오기)
                val accessToken = tokenManager.getAccessToken().first()
                Log.d("LOGOUT", "accessToken: $accessToken")

                if (accessToken == null) {
                    Log.d("LOGOUT", "토큰 없음 - 바로 클리어")
                    tokenManager.clearTokens()
                    onSuccess()
                    return@launch
                }

                authRepository.logout()
                    .onSuccess {
                        Log.d("LOGOUT", "서버 로그아웃 성공")
                        tokenManager.clearTokens()
                        onSuccess()
                    }
                    .onFailure {
                        Log.d("LOGOUT", "서버 로그아웃 실패: ${it.message}")
                        // 실패해도 로컬 토큰 삭제 후 로그인 화면으로
                        tokenManager.clearTokens()
                        onSuccess()
                    }
            } catch (e: Exception) {
                Log.d("LOGOUT", "예외 발생: ${e.message}")
                tokenManager.clearTokens()
                onSuccess()
            }
        }
    }
}