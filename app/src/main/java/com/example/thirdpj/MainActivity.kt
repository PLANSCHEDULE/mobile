package com.example.thirdpj

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.thirdpj.data.api.RetrofitClient
import com.example.thirdpj.data.post.dto.PostTemplateDto
import com.example.thirdpj.data.post.dto.PostTemplateItem
import com.example.thirdpj.data.profile.repository.ProfileRepository
import com.example.thirdpj.data.repository.AuthRepository
import com.example.thirdpj.ui.allview.screens.TemplateAllViewScreen
import com.example.thirdpj.ui.auth.login.LoginScreen
import com.example.thirdpj.ui.auth.login.LoginViewModel
import com.example.thirdpj.ui.auth.signup.SignUpScreen
import com.example.thirdpj.ui.auth.signup.SignUpViewModel
import com.example.thirdpj.ui.favorite.screens.FavoriteScreen
import com.example.thirdpj.ui.global.components.BottomBar
import com.example.thirdpj.ui.global.components.MainAddButton
import com.example.thirdpj.ui.home.screens.HomeScreen
import com.example.thirdpj.ui.home.screens.HomeViewModel
import com.example.thirdpj.ui.mypage.screens.MyPageScreen
import com.example.thirdpj.ui.plan.create.screens.CreatePlanScreen
import com.example.thirdpj.ui.profile.screens.ProfileScreen
import com.example.thirdpj.ui.profile.screens.ProfileViewModel
import com.example.thirdpj.ui.testdata.TemplateItemData
import com.example.thirdpj.ui.theme.ThirdPJTheme
import com.example.thirdpj.util.TokenManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // retrofitclient 초기화
        RetrofitClient.init(applicationContext)

        val tokenManager = TokenManager(applicationContext)


        enableEdgeToEdge()

        setContent {
            ThirdPJTheme {
                val navController = rememberNavController()

                val authService = RetrofitClient.authService
                val authRepository = AuthRepository(authService)

                val loginViewModel: LoginViewModel = viewModel { LoginViewModel(authRepository, tokenManager) }
                val signUpViewModel: SignUpViewModel = viewModel { SignUpViewModel(authRepository, tokenManager) }

                // 프로필 관련 서비스
                val profileService = RetrofitClient.profileService
                val profileRepository = ProfileRepository(profileService)
                val profileViewModel: ProfileViewModel = viewModel { ProfileViewModel(profileRepository) }
                // 현재가 어떤 화면에 있는지 실시간으로 가져옴
                // 로그인 화면과 같은데서는 하단바가 나오면 안됨. 그걸 처리하기 위해서 아래와 같은 변수로 처리가 필요함
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                // 하단바를 숨길 경로
                val hideBottomBarScreens = listOf("login", "signup", "profile_create", "top10_view")

                val showFabScreens = listOf("home", "favorite", "mypage")

                val homeViewModel: HomeViewModel = viewModel { HomeViewModel() }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    // 현재의 경로가 숨김 리스트에 없을 때만 하단바 보여주기
                    bottomBar = {
                        if (currentRoute !in hideBottomBarScreens) {
                            BottomBar(navController = navController)
                        }
                    },
                    floatingActionButton = {

                        if(currentRoute in showFabScreens) {
                            Box(
                                modifier = Modifier.offset(y =24.dp)
                            ) {
                                MainAddButton ( onClick = {
                                    navController.navigate("create_plan") {
                                        launchSingleTop = true
                                    }
                                })
                            }
                        }
                    },
                    floatingActionButtonPosition = FabPosition.Center



                ) {innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "login",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        // 로그인 화면 등록
                        composable("login") {
                            LoginScreen(
                                viewModel = loginViewModel,
                                onNavigateToSignUp = {navController.navigate("signup")},
                                onLoginSuccess = {
                                    navController.navigate("home") {
                                        popUpTo("login"){ inclusive = true }
                                    }
                                }
                            )
                        }

                        // 회원가입 화면 등록
                        composable("signup") {
                            SignUpScreen(
                                viewModel = signUpViewModel,
                                onBack = {navController.popBackStack()},
                                onSignUpSuccess = {
                                    navController.navigate("profile_create") {
                                        popUpTo("signup") {inclusive = true}
                                    }
                                }


                            )

                        }

                        // 프로필 생성 화면
                        composable("profile_create") {
                            ProfileScreen (
                                viewModel = profileViewModel,
                                initialProfile = null,
                                onSuccess = {
                                    navController.navigate("home") {
                                        popUpTo("profile_create") {
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }

                        composable("home") {
                            HomeScreen(
                                viewModel = homeViewModel,
                                onBrowseClick = {
                                    navController.navigate("top10_view")
                                }
                            )
                        }

                       composable("favorite"){
                           FavoriteScreen()
                       }

                        composable("top10_view") {

                            TemplateAllViewScreen(
                                title = "이번주 BEST TOP 10",
                                onBackClick = { navController.popBackStack() }
                            )

                        }


                        // 마이페이지 화면
                        // 연결은 안한건 맞는데 왜 하단바 누르면 앱이 종료되버리지
                        // 같이 연결아직안된 검색은 눌러도 종료는 안되는데 원인 찾아봐야 될 듯
                        composable("mypage") {
                            (
                                    MyPageScreen(
                                        navController = navController,
                                        viewModel = profileViewModel,
                                        onHeartClick = {navController.navigate("favorite")})
                            )
                        }

                        composable("create_plan"){
                            CreatePlanScreen(
                                onBackClick = {
                                    navController.popBackStack()
                                }

                            )
                        }

                    }


                }

            }
        }
    }
}

