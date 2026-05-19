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
            Log.d("HOME_RAW", response.body().toString())
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

    suspend fun getMyFavorites(page: Int, size: Int): Result<SliceResponse<PostTemplateDto>> {
        return try {
            val response = apiService.getMyFavorites(page = page, size = size)
            Log.d("FAVORITE_RAW", response.code().toString())
            Log.d("FAVORITE_RAW", response.errorBody()?.string() ?: "no error body")
            if (response.isSuccessful) {
                val data = response.body()?.data
                    ?: return Result.failure(Exception("응답 데이터가 없습니다."))
                Result.success(data)
            } else {
                Result.failure(Exception("찜 목록 조회 실패 코드: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPostTemplateDetail(postTemplateId: Long): Result<PostTemplateDto> {
        return try {
            val response = apiService.getPostTemplateDetail(postTemplateId)
            if (response.isSuccessful) {
                val data = response.body()?.data
                    ?: return Result.failure(Exception("응답 데이터가 없습니다."))
                Result.success(data)
            } else {
                Result.failure(Exception("상세 조회 실패 코드: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }




}