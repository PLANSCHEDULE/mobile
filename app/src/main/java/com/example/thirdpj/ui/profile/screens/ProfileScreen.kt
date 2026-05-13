package com.example.thirdpj.ui.profile.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.thirdpj.data.profile.dto.ProfileResponse
import com.example.thirdpj.ui.profile.components.ProfileTopBar
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun ProfileScreen(
    initialProfile: ProfileResponse?= null,
    onActionClick: (ProfileResponse) -> Unit = {},
    onBackClick: () -> Unit = {}

) {
    val isEditMode = initialProfile != null

    var handle by remember { mutableStateOf(initialProfile?.handle ?: "") }
    var nickname by remember { mutableStateOf(initialProfile?.nickname ?:"") }
    var bio by remember { mutableStateOf(initialProfile?.bio ?: "") }

    // 유효성 검사
    val isFormValid = handle.length >= 2 && nickname.isNotBlank()

    Scaffold(
        topBar = {
            ProfileTopBar(
                title = if(isEditMode) "프로필 수정" else "프로필 생성",
                actionText = if(isEditMode) "저장" else "완료",
                isActionEnabled = isFormValid,
                onActionClick = {
                    onActionClick(ProfileResponse(handle, nickname, bio))

                },
                onBackClick = onBackClick

            )
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {

        }

    }


}

@Preview(showBackground = true, name = "생성 모드")
@Composable
fun ProfileCreatePreview() {
    ThirdPJTheme {
        ProfileScreen(initialProfile = null)
    }
}

@Preview(showBackground = true, name = "수정 모드")
@Composable
fun ProfileEditPreview() {
    ThirdPJTheme {
        ProfileScreen(
            initialProfile = ProfileResponse(
                handle = "gemini_user",
                nickname = "제미니",
                bio = "안녕하세요! 안드로이드 개발 중입니다."
            )
        )
    }
}