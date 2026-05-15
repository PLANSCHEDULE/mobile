package com.example.thirdpj.data.api

import com.example.thirdpj.data.auth.dto.RefreshRequest
import com.example.thirdpj.util.TokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class AuthAuthenticator(
    private val tokenManager: TokenManager,
    private val authService: AuthService
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        //  만약 재시도를 3번 이상 했는데도 안 되면 포기
        //  무한 루프를 방지하기 위해서 설정
        if (responseCount(response) >= 3) return null

        // 저장된 Refresh Token 가져오기
        val refreshToken = runBlocking { tokenManager.getRefreshToken().first() }
        if (refreshToken.isNullOrEmpty()) return null

        // 백엔드 구조에 맞게 재발급 요청 시작
        val newAccessToken = runBlocking {
            try {
                // 백엔드 RequestBody로 RefreshRequest를 받는 상태로 정의
                val request = RefreshRequest(refreshToken)
                val refreshResponse = authService.refreshToken(request)

                if (refreshResponse.isSuccessful) {
                    // ApiResponse<LoginResponse> 형태이므로 .data를 꺼냄
                    val loginResponse = refreshResponse.body()?.data
                    val newAccess = loginResponse?.accessToken
                    val newRefresh = loginResponse?.refreshToken

                    if (newAccess != null && newRefresh != null) {
                        // 새로 받은 토큰들을 DataStore에 저장
                        tokenManager.saveTokens(newAccess, newRefresh)
                        newAccess
                    } else null
                } else null
            } catch (e: Exception) {
                null
            }
        }

        // 새로운 토큰을 얻었다면, 실패했던 요청의 헤더만 바꿔서 재시도
        return if (newAccessToken != null) {
            response.request().newBuilder()
                .header("Authorization", "Bearer $newAccessToken")
                .build()
        } else {
            // 리프레시 토큰도 만료되었다면 로그아웃 처리
            runBlocking { tokenManager.clearTokens() }
            null
        }
    }

    private fun responseCount(response: Response): Int {
        var result = 1
        var priorResponse = response.priorResponse()
        while (priorResponse != null) {
            result++
            priorResponse = priorResponse.priorResponse()
        }
        return result
    }

}