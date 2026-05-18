package com.example.thirdpj.data.post.dto

data class SliceResponse<T>(
    val content: List<T>,
    val pageable: Any?,
    val last: Boolean,
    val first: Boolean,
    val empty: Boolean
)
