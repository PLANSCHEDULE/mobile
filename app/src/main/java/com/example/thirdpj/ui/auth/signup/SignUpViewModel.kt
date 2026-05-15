package com.example.thirdpj.ui.auth.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thirdpj.data.auth.dto.LoginResponse
import com.example.thirdpj.data.auth.dto.SignUpRequest
import com.example.thirdpj.data.repository.AuthRepository
import com.example.thirdpj.util.TokenManager
import com.example.thirdpj.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(private val repository: AuthRepository,
                      private val tokenManager: TokenManager) : ViewModel() {
    private val _signUpState = MutableStateFlow<UiState<LoginResponse>>(UiState.Idle)
    val signUpState: StateFlow<UiState<LoginResponse>> = _signUpState


    fun signUp(name: String, email: String, password: String) {
        viewModelScope.launch {
            // 로딩 상태로 변경
            _signUpState.value = UiState.Loading

            try {
                // Repository를 통해 API 호출
                val request = SignUpRequest(name, email, password)
                val response = repository.signUp(request)

                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d("API_SUCCESS", "응답 데이터: ${body?.data}")
                    // 백엔드에서 회원가입에 성공하면 201을 반환 예정
                    if (body != null && body.status == 201) {

                        // 회원가입 성공 -> 토큰 필요 -> 프로필 제작 페이지
                        val data : LoginResponse = body.data!!
                        tokenManager.saveTokens(
                            accessToken = data.accessToken,
                            refreshToken = data.refreshToken
                        )

                        _signUpState.value = UiState.Success(data)

                    } else {
                        _signUpState.value = UiState.Error(body?.message ?: "회원가입 정보를 확인해주세요.")
                    }
                } else {
                    _signUpState.value = UiState.Error("서버 오류가 발생했습니다. (코드: ${response.code()})")
                }
            } catch (e: Exception) {
                // 네트워크 연결 끊김 등 예외 처리
                _signUpState.value = UiState.Error("네트워크 연결을 확인해주세요.")
            }
        }
    }


    fun resetState() {
        _signUpState.value = UiState.Idle
    }
}