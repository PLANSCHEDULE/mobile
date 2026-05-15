package com.example.thirdpj.data.api

import com.example.thirdpj.util.TokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

// 모든 retrofit 요청을 가로채서 헤더에 access token을 추가하는 클래스임
class AuthInterceptor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // 현재 진행 중인 request 요청을 가져옴
        val originalRequest = chain.request()

        //  토큰이 필요없는 api는 통과 (로그인, 회원가입, refresh)
        if (originalRequest.url().encodedPath().contains("/api/auth/login") ||
            originalRequest.url().encodedPath().contains("/api/auth/signup") ||
            originalRequest.url().encodedPath().contains("/api/auth/refresh"))
        {

            return chain.proceed(originalRequest)
        }

        // TokenManager에서 현재 AccessToken을 동기적으로 가져옴
        val accessToken = runBlocking {
            tokenManager.getAccessToken().first()
        }

        // 토큰이 있다면 헤더에 추가해서 전송, 없으면 그냥 전송
        val requestBuilder = originalRequest.newBuilder()
        if (!accessToken.isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $accessToken")
        }

        // 헤더가 추가된 새로운 요청을 실행
        return chain.proceed(requestBuilder.build())
    }
}