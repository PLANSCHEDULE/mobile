package com.example.thirdpj.data.template.api

import com.example.thirdpj.data.model.GlobalDto.ApiResponse
import com.example.thirdpj.data.template.dto.TemplateCreateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TemplateApiService {
    @POST("api/templates")
    suspend fun createTemplate(
        @Body request: TemplateCreateRequest
    ) : Response<ApiResponse<Unit>>
}