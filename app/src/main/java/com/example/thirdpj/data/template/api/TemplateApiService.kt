package com.example.thirdpj.data.template.api

import com.example.thirdpj.data.model.GlobalDto.ApiResponse
import com.example.thirdpj.data.post.dto.PostTemplateDto
import com.example.thirdpj.data.post.dto.SliceResponse
import com.example.thirdpj.data.template.dto.TemplateCreateRequest
import com.example.thirdpj.data.template.dto.TemplateResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TemplateApiService {
    @POST("api/templates")
    suspend fun createTemplate(
        @Body request: TemplateCreateRequest
    ) : Response<ApiResponse<TemplateResponse>>

    @POST("api/posts/share/{templateId}")
    suspend fun shareTemplate(
        @Path("templateId") templateId: Long
    ) : Response<ApiResponse<PostTemplateDto>>

    @GET("api/templates")
    suspend fun getMyAllTemplates(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10
    ) : Response<ApiResponse<SliceResponse<TemplateResponse>>>

    @GET("api/templates/me/downloaded")
    suspend fun getMyDownloadedTemplates(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10
    ): Response<ApiResponse<SliceResponse<TemplateResponse>>>

    @GET("api/templates/{templateId}")
    suspend fun getTemplateDetail(
        @Path("templateId") templateId: Long
    ): Response<ApiResponse<TemplateResponse>>
}