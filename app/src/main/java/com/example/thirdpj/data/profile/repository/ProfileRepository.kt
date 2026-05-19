package com.example.thirdpj.data.profile.repository

import com.example.thirdpj.data.profile.api.ProfileService
import com.example.thirdpj.data.profile.dto.ProfileRequest
import com.example.thirdpj.data.profile.dto.ProfileResponse
import com.example.thirdpj.data.profile.dto.ProfileUpdateRequest

class ProfileRepository(private val profileService: ProfileService) {
    suspend fun createProfile(handle: String, nickname: String, bio: String): Result<ProfileResponse> {
        return try {
            val response = profileService.createProfile(ProfileRequest(handle, nickname, bio))
            if (response.isSuccessful) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception("프로필 생성 실패: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getMyProfile(): Result<ProfileResponse> {
        return try {
            val response = profileService.getMyProfile()
            if (response.isSuccessful) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception("프로필 조회 실패: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateProfile(nickname: String, bio: String): Result<ProfileResponse> {
        return try {
            val response = profileService.updateProfile(ProfileUpdateRequest(nickname, bio))
            if (response.isSuccessful) {
                Result.success(response.body()!!.data!!)
            } else {
                Result.failure(Exception("프로필 수정 실패: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }



}