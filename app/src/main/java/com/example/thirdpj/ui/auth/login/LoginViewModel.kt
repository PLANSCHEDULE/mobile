package com.example.thirdpj.ui.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thirdpj.data.auth.dto.LoginRequest
import com.example.thirdpj.data.auth.dto.LoginResponse
import com.example.thirdpj.data.repository.AuthRepository
import com.example.thirdpj.util.TokenManager
import com.example.thirdpj.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthRepository,
                     private val tokenManager: TokenManager): ViewModel(){
    private val _loginState = MutableStateFlow<UiState<LoginResponse>>(UiState.Idle)
    val loginState: StateFlow<UiState<LoginResponse>> = _loginState

    fun login(email: String, password: String) {
        // viewmodelscope는 네트워크 요청
        // 아까 signup 초기에 만들 때 Coroutine 썼던거를
        // viewModelScope.launch가 그 역할을 자동으로 해주게 됨
        viewModelScope.launch {
            // 로딩 시작 상태
            _loginState.value = UiState.Loading
            try {
                // 백엔드에 데이터 요청
                val response = repository.login(LoginRequest(email, password))
                if(response.isSuccessful) {
                    val body = response.body()
                    if(body != null && body.status == 200) {
                        Log.d("LOGIN_API", "로그인 성공 바디: ${body?.data}")

                        // 로그인 성공 시 토큰 저장
                        val data = body.data!!
                        tokenManager.saveTokens(
                            accessToken = data.accessToken,
                            refreshToken = data.refreshToken,
                            userId = data.userId
                        )

                        // 서버에서 데이터를 잘 받아왔다면
                        _loginState.value = UiState.Success(body.data!!)
                    } else {
                        _loginState.value = UiState.Error(body?.message ?: "로그인 실패")
                    }
                } else {
                    _loginState.value = UiState.Error("서버 에러: ${response.code()}")
                }
            } catch (e: Exception) {
                _loginState.value = UiState.Error("네트워크 오류가 발생했습니다.")
            }
        }

    }



}