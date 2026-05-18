package com.example.thirdpj.data.post.api

import com.example.thirdpj.data.model.GlobalDto.ApiResponse
import com.example.thirdpj.data.post.dto.PostTemplateDto
import com.example.thirdpj.data.post.dto.SliceResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PostTemplateApiService {
    @GET("api/posts/paging")
    suspend fun getAllTemplates(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10,
        @Query("sort") sort: String ="createdAt,desc"
    ) : Response<ApiResponse<SliceResponse<PostTemplateDto>>>

    @GET("api/posts/top10")
    suspend fun getTop10Templates() : Response<ApiResponse<List<PostTemplateDto>>>

    // 다운로드 관련 api
    @POST("api/posts/{postTemplateId}/download")
    suspend fun downloadTemplate(
        @Path("postTemplateId") postTemplateId: Long,
    ) : Response<ApiResponse<Unit>>

    // 찜 토클
    @POST("api/favorites/{postTemplateId}")
    suspend fun toggleFavorite(
        @Path("postTemplateId") postTemplateId: Long
    ) : Response<ApiResponse<Unit>>

    @GET("api/favorites/me")
    suspend fun getMyFavorites(
        @Query("page") page: Int = 0,
        @Query("size") size: Int = 10
    ) : Response<ApiResponse<SliceResponse<PostTemplateDto>>>
}