package com.example.thirdpj.data.post.api

import com.example.thirdpj.data.model.GlobalDto.ApiResponse
import com.example.thirdpj.data.post.dto.PostTemplateDto
import com.example.thirdpj.data.post.dto.SliceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostTemplateApiService {
    @GET("paging")
    suspend fun getAllTemplates(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
        @Query("sort") sort: String ="createdAt,desc"
    ) : Response<ApiResponse<SliceResponse<PostTemplateDto>>>
}