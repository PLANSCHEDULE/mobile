package com.example.thirdpj.ui.profile.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thirdpj.data.profile.dto.ProfileResponse
import com.example.thirdpj.data.profile.repository.ProfileRepository
import com.example.thirdpj.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<ProfileResponse>>(UiState.Idle)
    val uiState: StateFlow<UiState<ProfileResponse>> = _uiState

    private val _profile = MutableStateFlow<ProfileResponse?>(null)
    val profile: StateFlow<ProfileResponse?> = _profile.asStateFlow()
    fun createProfile(handle: String, nickname: String, bio: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            val result = repository.createProfile(handle, nickname, bio)

            result.onSuccess {
                _uiState.value = UiState.Success(it)
            }.onFailure {
                _uiState.value = UiState.Error(it.message ?: "프로필 생성 중 오류가 발생했습니다.")
            }
        }
    }

    fun resetState() {
        _uiState.value = UiState.Idle
    }

    fun fetchMyProfile() {
        viewModelScope.launch {
            repository.getMyProfile()
                .onSuccess { _profile.value = it }
                .onFailure { it.printStackTrace() }
        }
    }

}