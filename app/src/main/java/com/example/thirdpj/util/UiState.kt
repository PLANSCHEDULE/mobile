package com.example.thirdpj.util

sealed class UiState<out T> {
    // 화면에 보여줄 데이터를 어떤 상황인가를 Idle, Loading, success, error 관점으로 보여주는 역할

    // Idle는 대기 상태. 아무런 작업을 시작도 않한 초기 상태
    object Idle: UiState<Nothing>()

    // 로딩 중
    // 서버에서 데이터를 가져오는 등 시간이 걸리는 작업
    // 흠.. 로딩 화면도 새로 만들어야 되는건가..? 찾아봐야할 듯
    object Loading: UiState<Nothing>()

    // success 성공
    // 작업을 바친 상태
    data class Success<T>(val data: T) : UiState<T>()

    // 에러
    data class Error(val message: String): UiState<Nothing>()
}