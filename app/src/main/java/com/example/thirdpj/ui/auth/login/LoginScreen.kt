package com.example.thirdpj.ui.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirdpj.ui.theme.ThirdPJTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onNavigateToSignUp: () -> Unit,
                onLoginSuccess: () -> Unit) {
    var isEmailLoginVisible by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("로그인") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Yellow
                )
            )
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                // 로그인 시에도 키보드 때문에 가려줄 수 있기 때문에 스크롤 추가
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            // 우선 oauth도 추가될 수 있으니 이메일로 로그인 버튼 따로 만들기
            if(!isEmailLoginVisible) {
                // 로그인 화면에 처음 들어갔을 때 보이는 로그인 버튼
                Button(
                    onClick = {isEmailLoginVisible = true},
                    // 버튼이 너무 양 옆일 때 해결 방법: Column에 padding 적절히 주기. 안 주면 너무 양옆에 딱 붙어버림
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("이메일로 로그인하기")
                }

                // 회원가입 이동
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 24.dp),
                    thickness = 1.dp,
                    color = Color.LightGray
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "아직 계정이 없으신가요?",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                    TextButton(
                        onClick = onNavigateToSignUp
                    ) {
                        Text(
                            text = "회원가입",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }


            } else {
                Text(
                    text = "이메일로 로그인",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 24.dp)
                )
                // 이메일 입력 칸
                // 버튼을 누르면 로그인 버튼이 안없어 지고 아래에 입력칸이 생기는게 더 좋을 것 같은데
                // 방법 찾아보기
                OutlinedTextField(
                    value = email,
                    onValueChange = {email = it},
                    label = {Text("이메일")},
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 비밀번호 입력 칸
                OutlinedTextField(
                    value = password,
                    onValueChange = {password = it},
                    label = {Text("비밀번호")},
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                // 로그인 버튼
                Button(
                    onClick = {
                        onLoginSuccess()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("로그인")
                }

                // 만약 잘못 눌렀을 경우 돌아가는 버튼도 필요할 듯!
                Button(
                    onClick = {isEmailLoginVisible = false},
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("취소")
                }

            }
        }
    }
}
//
//@Preview
//@Composable
//fun LoginScreenPreview() {
//    ThirdPJTheme{
//        LoginScreen(onNavigateToSignup = {})
//    }
//}