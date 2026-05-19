package com.example.thirdpj.ui.mypage.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.thirdpj.ui.mypage.MyPageViewModel
import com.example.thirdpj.ui.mypage.components.HeartTemplateCard
import com.example.thirdpj.ui.mypage.components.MyTemplateSection
import com.example.thirdpj.ui.mypage.components.ProfileHeaderScreen
import com.example.thirdpj.ui.profile.screens.ProfileViewModel
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun MyPageScreen(navController: NavController,
                 viewModel: ProfileViewModel,
                 myPageViewModel: MyPageViewModel = viewModel(),
                 onHeartClick: () -> Unit = {},
                 onTemplateClick:(Long) -> Unit = {},
                 onLogoutClick: () -> Unit = {}) {

    val profile by viewModel.profile.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val myTemplates by myPageViewModel.myTemplates.collectAsStateWithLifecycle()
    val downloadedTemplates by myPageViewModel.downloadedTemplates.collectAsStateWithLifecycle()


    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            viewModel.fetchMyProfile()
            myPageViewModel.fetchMyTemplates(isRefresh = true)
            myPageViewModel.fetchDownloadedTemplates(isRefresh = true)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFFDF9F6))
    ) {
        ProfileHeaderScreen(
            nickname = profile?.nickname ?: "",
            handle = profile?.handle ?: "",
            bio = profile?.bio ?: "",
            onLogoutClick = onLogoutClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        HeartTemplateCard(
            onClick = onHeartClick
        )

        Spacer(modifier = Modifier.height(24.dp))

        MyTemplateSection(
            title = "내 템플릿",
            count = myTemplates.size.toString(),
            countColor = Color(0xFFFF5252),
            templates = myTemplates,
            onCardClick = { id -> onTemplateClick(id) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        MyTemplateSection(
            title = "포크한 템플릿",
            count = downloadedTemplates.size.toString(),
            countColor = Color(0xFF4A90E2),
            templates = downloadedTemplates,
            onCardClick = { id -> onTemplateClick(id) }
        )

        Spacer(modifier = Modifier.height(30.dp))
    }

}

//@Preview
//@Composable
//fun MyPageScreenPreview() {
//    ThirdPJTheme {
//        MyPageScreen(navController = rememberNavController())
//    }
//}