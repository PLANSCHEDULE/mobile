package com.example.thirdpj.data.post.repository

import com.example.thirdpj.data.post.api.PostTemplateApiService
import com.example.thirdpj.data.post.dto.PostTemplateDto
import com.example.thirdpj.data.post.dto.SliceResponse

class PostTemplateRepository(private val apiService: PostTemplateApiService) {

    suspend fun getAllTemplates(page: Int, size: Int): Result<SliceResponse<PostTemplateDto>> {
        return try {
            val response = apiService.getAllTemplates(page = page, size = size)

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
}