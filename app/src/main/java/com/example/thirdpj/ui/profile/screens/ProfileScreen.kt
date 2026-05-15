package com.example.thirdpj.ui.profile.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirdpj.data.profile.dto.ProfileResponse
import com.example.thirdpj.ui.profile.components.ProfileForm
import com.example.thirdpj.ui.profile.components.ProfileTopBar
import com.example.thirdpj.ui.theme.ThirdPJTheme
import com.example.thirdpj.util.UiState

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    initialProfile: ProfileResponse?= null,
    onActionClick: (ProfileResponse) -> Unit = {},
    onSuccess: () -> Unit = {},
    onBackClick: () -> Unit = {}

) {
    val isEditMode = initialProfile != null
    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsState()

    // 서버 응답 처리
    LaunchedEffect(uiState) {
        if (uiState is UiState.Success) {
            onSuccess() // 홈으로 이동
        } else if (uiState is UiState.Error) {
            Toast.makeText(context, (uiState as UiState.Error).message, Toast.LENGTH_SHORT).show()
            viewModel.resetState()
        }
    }

    BackHandler(enabled = !isEditMode) {
        Toast.makeText(context, "원활한 서비스 이용을 위해 프로필 작성이 필요합니다.", Toast.LENGTH_SHORT).show()
    }

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
                    if (isEditMode) {
                        // 수정 모드
                        // 임시 데이터
                        onActionClick(
                            ProfileResponse(
                                userId = initialProfile!!.userId,
                                handle = handle,
                                nickname = nickname,
                                bio = bio
                            )
                        )
                    } else {
                        // 생성모드일 때, 서버에 직접 데이터 전송 요청
                        viewModel.createProfile(handle, nickname, bio)
                    }

                },
                onBackClick = onBackClick,
                showBackButton = isEditMode

            )
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ProfileForm(
                handle = handle,
                onHandleChange = {if(it.length <= 50) handle = it},
                isHandleEditable = !isEditMode,
                nickname = nickname,
                onNicknameChange = {if(it.length <= 20) nickname = it},
                bio = bio,
                onBioChange = {if(it.length <=150) bio = it}
            )
                Spacer(modifier = Modifier.height(40.dp))
        }

    }

}

//@Preview(showBackground = true, name = "생성 모드")
//@Composable
//fun ProfileCreatePreview() {
//    ThirdPJTheme {
//        ProfileScreen(initialProfile = null)
//    }
//}

//@Preview(showBackground = true, name = "수정 모드")
//@Composable
//fun ProfileEditPreview() {
//    ThirdPJTheme {
//        ProfileScreen(
//            initialProfile = ProfileResponse(
//                handle = "gildong",
//                nickname = "홍길동",
//                bio = "안녕하세요! 안드로이드 개발 중입니다."
//            )
//        )
//    }
//}