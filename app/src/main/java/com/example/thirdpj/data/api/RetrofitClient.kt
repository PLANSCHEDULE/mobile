package com.example.thirdpj.data.api

import android.content.Context
import com.example.thirdpj.data.api.AuthService
import com.example.thirdpj.util.TokenManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

// 통신을 담당하는 역할
object RetrofitClient {
    // retrofit은 baseurl에 / 붙이는 걸 권장.
    // 그럼 authservice단에 api주소도 / 빼야되는건가 -> 빼야됨
    private const val BASE_URL = "http://10.0.2.2:8080/"

    private var tokenManager: TokenManager? = null
    private lateinit var retrofit: Retrofit

    fun init(context: Context) {
        tokenManager = TokenManager(context)

        // 헤더에 토큰을 붙여주는 intercetpor 설정
        val interceptor = AuthInterceptor(tokenManager!!)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .authenticator { route, response ->
                AuthAuthenticator(tokenManager!!, authService).authenticate(route, response)

            }
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }
}