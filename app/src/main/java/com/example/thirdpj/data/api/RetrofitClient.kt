package com.example.thirdpj.data.api

import android.content.Context
import com.example.thirdpj.data.post.api.PostTemplateApiService
import com.example.thirdpj.data.profile.api.ProfileService
import com.example.thirdpj.data.template.api.TemplateApiService
import com.example.thirdpj.util.TokenManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.thirdpj.BuildConfig

// 통신을 담당하는 역할
object RetrofitClient {
    // retrofit은 baseurl에 / 붙이는 걸 권장.
    // 그럼 authservice단에 api주소도 / 빼야되는건가 -> 빼야됨
    private const val BASE_URL = BuildConfig.BASE_URL
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

    //인증 서비스
    val authService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }

    // 프로필 서비스
    val profileService: ProfileService by lazy {
        retrofit.create(ProfileService::class.java)
    }

    // 템플릿 서비스
    val  templateApiService: TemplateApiService by lazy {
        retrofit.create(TemplateApiService::class.java)
    }

    // Post(공유된) 템플릿 서비스
    val postTemplateApiService: PostTemplateApiService by lazy {
        retrofit.create(PostTemplateApiService::class.java)
    }
}