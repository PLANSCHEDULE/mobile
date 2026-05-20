package com.example.thirdpj.data.template.repository

import com.example.thirdpj.data.post.dto.SliceResponse
import com.example.thirdpj.data.template.api.TemplateApiService
import com.example.thirdpj.data.template.dto.TemplateCreateRequest
import com.example.thirdpj.data.template.dto.TemplateResponse
import com.example.thirdpj.data.template.dto.TemplateUpdateRequest

class TemplateRepository(private val templateService: TemplateApiService) {

    suspend fun createTemplate(request: TemplateCreateRequest): Result<TemplateResponse> {
        return try {
            val response = templateService.createTemplate(request)
            if (response.isSuccessful) {
                val data = response.body()?.data
                    ?: return Result.failure(Exception("응답 데이터가 없습니다."))
                Result.success(data)
            } else {
                Result.failure(Exception("템플릿 생성 실패 코드: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun shareTemplate(templateId: Long): Result<Unit> {
        return try {
            val response = templateService.shareTemplate(templateId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("공유 실패 코드: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getMyAllTemplates(page: Int, size: Int): Result<SliceResponse<TemplateResponse>> {
        return try {
            val response = templateService.getMyAllTemplates(page = page, size = size)
            if (response.isSuccessful) {
                val data = response.body()?.data
                    ?: return Result.failure(Exception("응답 데이터가 없습니다."))
                Result.success(data)
            } else {
                Result.failure(Exception("내 템플릿 조회 실패 코드: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // 내가 다운로드한 템플릿
    suspend fun getMyDownloadedTemplates(page: Int, size: Int): Result<SliceResponse<TemplateResponse>> {
        return try {
            val response = templateService.getMyDownloadedTemplates(page = page, size = size)
            if (response.isSuccessful) {
                val data = response.body()?.data
                    ?: return Result.failure(Exception("응답 데이터가 없습니다."))
                Result.success(data)
            } else {
                Result.failure(Exception("다운로드 템플릿 조회 실패 코드: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getTemplateDetail(templateId: Long): Result<TemplateResponse> {
        return try {
            val response = templateService.getTemplateDetail(templateId)
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

    suspend fun updateTemplate(templateId: Long, request: TemplateUpdateRequest): Result<TemplateResponse> {
        return try {
            val response = templateService.updateTemplate(templateId, request)
            if (response.isSuccessful) {
                val data = response.body()?.data
                    ?: return Result.failure(Exception("응답 데이터가 없습니다."))
                Result.success(data)
            } else {
                Result.failure(Exception("수정 실패 코드: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteTemplate(templateId: Long): Result<Unit> {
        return try {
            val response = templateService.deleteTemplate(templateId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("삭제 실패 코드: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}