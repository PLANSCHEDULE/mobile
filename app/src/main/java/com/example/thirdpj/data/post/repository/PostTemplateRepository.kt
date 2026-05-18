package com.example.thirdpj.data.post.repository

import android.util.Log
import com.example.thirdpj.data.post.api.PostTemplateApiService
import com.example.thirdpj.data.post.dto.PostTemplateDto
import com.example.thirdpj.data.post.dto.SliceResponse

class PostTemplateRepository(private val apiService: PostTemplateApiService) {

    suspend fun getAllTemplates(page: Int, size: Int): Result<SliceResponse<PostTemplateDto>> {
        return try {
            val response = apiService.getAllTemplates(page = page, size = size)
            val rawBody = response.errorBody()?.string() ?: response.body().toString()
            Log.d("API_RAW", rawBody)
            if (response.isSuccessful) {
                val apiResponse = response.body()

                if (apiResponse?.data != null) {
                    Result.success(apiResponse.data)
                } else {
                    Result.failure(Exception("서버 응답 데이터가 비어있습니다 (data가 null)."))
                }
            } else {
                Result.failure(Exception("템플릿 조회 실패 코드: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // top 10
    suspend fun getTop10Templates() : Result<List<PostTemplateDto>> {
        return runCatching {
            val response = apiService.getTop10Templates()
            response.body()?.data ?:emptyList()
        }
    }

    // 다운로드
    suspend fun downloadTemplate(postTemplateId: Long): Result<Unit> {
        return try {
            val response = apiService.downloadTemplate(postTemplateId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("다운로드 실패 코드: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // 찜 토글
    suspend fun toggleFavorite(postTemplateId: Long): Result<Unit> {
        return try {
            val response = apiService.toggleFavorite(postTemplateId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("찜 토글 실패 코드: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }




}