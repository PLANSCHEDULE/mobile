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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thirdpj.ui.auth.login.LoginScreen
import com.example.thirdpj.ui.auth.signup.SignUpScreen
import com.example.thirdpj.ui.profile.screens.ProfileScreen
import com.example.thirdpj.ui.theme.ThirdPJTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThirdPJTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {
                    // 로그인 화면 등록
                    composable("login") {
                        LoginScreen(
                            onNavigateToSignUp = {navController.navigate("signup")}
                        )
                    }
                    // 회원가입 화면 등록
                    composable("signup") {
                        SignUpScreen(
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
                                // 저장 성공 후 메인 화면 이동 필요

                            }
                        )
                    }


                }
            }
        }
    }
}

