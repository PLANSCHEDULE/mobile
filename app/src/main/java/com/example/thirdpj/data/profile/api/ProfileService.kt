package com.example.thirdpj.data.profile.api

import com.example.thirdpj.data.model.GlobalDto.ApiResponse
import com.example.thirdpj.data.profile.dto.ProfileRequest
import com.example.thirdpj.data.profile.dto.ProfileResponse
import com.example.thirdpj.data.profile.dto.ProfileUpdateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ProfileService {
    @POST("api/profiles")
    suspend fun createProfile(
        @Body request: ProfileRequest
    ): Response<ApiResponse<ProfileResponse>>


    @GET("api/profiles")
    suspend fun getMyProfile(): Response<ApiResponse<ProfileResponse>>

    @PATCH("api/profiles")
    suspend fun updateProfile(
        @Body request: ProfileUpdateRequest
    ): Response<ApiResponse<ProfileResponse>>
}