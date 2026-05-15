package com.example.thirdpj

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.thirdpj.data.api.RetrofitClient
import com.example.thirdpj.data.repository.AuthRepository
import com.example.thirdpj.ui.allview.screens.TemplateAllViewScreen
import com.example.thirdpj.ui.auth.login.LoginScreen
import com.example.thirdpj.ui.auth.login.LoginViewModel
import com.example.thirdpj.ui.auth.signup.SignUpScreen
import com.example.thirdpj.ui.auth.signup.SignUpViewModel
import com.example.thirdpj.ui.global.components.BottomBar
import com.example.thirdpj.ui.home.screens.HomeScreen
import com.example.thirdpj.ui.mypage.screens.MyPageScreen
import com.example.thirdpj.ui.profile.screens.ProfileScreen
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
                val signUpViewModel: SignUpViewModel = viewModel { SignUpViewModel(authRepository) }

                // 현재가 어떤 화면에 있는지 실시간으로 가져옴
                // 로그인 화면과 같은데서는 하단바가 나오면 안됨. 그걸 처리하기 위해서 아래와 같은 변수로 처리가 필요함
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                // 하단바를 숨길 경로
                val hideBottomBarScreens = listOf("login", "signup", "profile_create")

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    // 현재의 경로가 숨김 리스트에 없을 때만 하단바 보여주기
                    bottomBar = {
                        if (currentRoute !in hideBottomBarScreens) {
                            BottomBar(navController = navController)
                        }
                    }

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
                                initialProfile = null,
                                onBackClick = {navController.popBackStack()},
                                onActionClick = {profileData ->
                                    navController.navigate("home") {
                                        popUpTo("profile_create") {
                                            inclusive = true
                                        }
                                    }

                                }
                            )
                        }

                        composable("home") {
                            HomeScreen()
                        }

                        // 찜 화면 연결해야됨
                        composable("favorite") {
                            //테스트용 더미 데이터. 나중에 viewmodel로 백엔드와 연결 예정
                            val dummyList = listOf(
                                TemplateItemData(1, "일본 당일치기 도쿄", "길동", "@gildong", "1234", "123", listOf("09:00" to "공항", "12:00" to "식사")),
                                TemplateItemData(2, "제주도 힐링 여행", "철수", "@chulsoo", "500", "50", listOf("10:00" to "제주공항", "14:00" to "카페")),
                                TemplateItemData(3, "서울 밤도깨비 야시장", "영희", "@younghee", "2.5k", "400", listOf("18:00" to "여의도", "20:00" to "푸드트럭")),
                                TemplateItemData(4, "속초 만석닭강정 투어", "미애", "@miae", "99", "10", listOf("11:00" to "중앙시장", "15:00" to "속초해변")),
                                TemplateItemData(5, "전주 한옥마을 정복", "도령", "@doryung", "1.1k", "220", listOf("12:00" to "경기전", "14:00" to "비빔밥")),
                                TemplateItemData(6, "경주 황리단길 산책", "박사", "@doctor", "880", "150", listOf("10:00" to "첨성대", "13:00" to "십원빵"))
                            )

                            TemplateAllViewScreen(
                                title = "찜한 템플릿",
                                templates = dummyList,
                                onBackClick = {
                                    navController.popBackStack()
                                },
                                onCardClick = {templateId ->
                                    // 여기는 상세페이지 이동으로 설정해야됨. 아직 ui 구현 못함..
                                }
                            )
                        }


                        // 마이페이지 화면
                        // 연결은 안한건 맞는데 왜 하단바 누르면 앱이 종료되버리지
                        // 같이 연결아직안된 검색은 눌러도 종료는 안되는데 원인 찾아봐야 될 듯
                        composable("mypage") {
                            (
                                    MyPageScreen(navController = navController)
                            )
                        }

                    }


                }

            }
        }
    }
}

