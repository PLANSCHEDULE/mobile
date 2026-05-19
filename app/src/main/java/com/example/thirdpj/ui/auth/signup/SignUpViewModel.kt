package com.example.thirdpj.ui.auth.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thirdpj.data.auth.dto.LoginResponse
import com.example.thirdpj.data.auth.dto.SignUpRequest
import com.example.thirdpj.data.model.ErrorResponse
import com.example.thirdpj.data.repository.AuthRepository
import com.example.thirdpj.util.TokenManager
import com.example.thirdpj.util.UiState
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val repository: AuthRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    private val _signUpState = MutableStateFlow<UiState<LoginResponse>>(UiState.Idle)
    val signUpState: StateFlow<UiState<LoginResponse>> = _signUpState

    fun signUp(name: String, email: String, password: String) {
        viewModelScope.launch {
            _signUpState.value = UiState.Loading

            try {
                val request = SignUpRequest(name, email, password)
                val response = repository.signUp(request)

                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d("API_SUCCESS", "응답 데이터: ${body?.data}")

                    if (body != null && body.status == 201) {
                        val data: LoginResponse = body.data!!
                        tokenManager.saveTokens(
                            accessToken = data.accessToken,
                            refreshToken = data.refreshToken,
                            userId = data.userId
                        )
                        _signUpState.value = UiState.Success(data)
                    } else {
                        _signUpState.value = UiState.Error(body?.message ?: "회원가입 정보를 확인해주세요.")
                    }
                } else {
                    val errorMessage = try {
                        val errorBody = response.errorBody()?.string()
                        val gson = Gson()
                        val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
                        errorResponse?.message ?: "서버 오류가 발생했습니다."
                    } catch (e: Exception) {
                        "서버 오류가 발생했습니다. (코드: ${response.code()})"
                    }
                    _signUpState.value = UiState.Error(errorMessage)
                }
            } catch (e: Exception) {
                _signUpState.value = UiState.Error("네트워크 연결을 확인해주세요.")
            }
        }
    }

    fun resetState() {
        _signUpState.value = UiState.Idle
    }
}