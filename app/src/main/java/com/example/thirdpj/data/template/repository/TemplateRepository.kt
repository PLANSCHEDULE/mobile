package com.example.thirdpj.data.template.repository

import com.example.thirdpj.data.template.api.TemplateApiService
import com.example.thirdpj.data.template.dto.TemplateCreateRequest

class TemplateRepository(private val templateService: TemplateApiService) {

    suspend fun createTemplate(request: TemplateCreateRequest): Result<Unit> {
        return try {
            val response = templateService.createTemplate(request)

            if (response.isSuccessful) {
                val apiResponse = response.body()

                // create는 201로 반환할 거임
                // isSuccessful 만 해도 200번대를 다 커버함
                if (response.isSuccessful) {
                    Result.success(Unit)
                } else {
                    Result.failure(Exception(apiResponse?.message ?: "서버 비즈니스 로직 실패"))
                }
            } else {
                Result.failure(Exception("템플릿 생성 실패 코드: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}