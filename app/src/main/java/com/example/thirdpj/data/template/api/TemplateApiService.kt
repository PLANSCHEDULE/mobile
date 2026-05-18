package com.example.thirdpj.data.template.api

import com.example.thirdpj.data.model.GlobalDto.ApiResponse
import com.example.thirdpj.data.post.dto.PostTemplateDto
import com.example.thirdpj.data.template.dto.TemplateCreateRequest
import com.example.thirdpj.data.template.dto.TemplateResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface TemplateApiService {
    @POST("api/templates")
    suspend fun createTemplate(
        @Body request: TemplateCreateRequest
    ) : Response<ApiResponse<TemplateResponse>>

    @POST("api/posts/share/{templateId}")
    suspend fun shareTemplate(
        @Path("templateId") templateId: Long
    ) : Response<ApiResponse<PostTemplateDto>>
}