package com.example.thirdpj.ui.auth.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirdpj.ui.theme.ThirdPJTheme
import com.example.thirdpj.util.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: LoginViewModel,
                onNavigateToSignUp: () -> Unit,
                onLoginSuccess: () -> Unit) {
    val loginState by viewModel.loginState.collectAsState()

    var isEmailLoginVisible by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // 로그인 성공 감시
    LaunchedEffect(loginState) {
        if(loginState is UiState.Success) {
            onLoginSuccess()
        }
    }
    Scaffold(
        containerColor = Color(0xFFF5F3FF)

    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp)
                .fillMaxSize()
                // 로그인 시에도 키보드 때문에 가려줄 수 있기 때문에 스크롤 추가
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFAFA9EC)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "PickPlan",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF534AB7)
            )

            Text(
                text = "오늘도 갓생 따라하기 🔥",
                fontSize = 14.sp,
                color = Color(0xFF7F77DD),
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))

            // 우선 oauth도 추가될 수 있으니 이메일로 로그인 버튼 따로 만들기
            if(!isEmailLoginVisible) {
                // 로그인 화면에 처음 들어갔을 때 보이는 로그인 버튼
                Button(
                    onClick = {isEmailLoginVisible = true},
                    // 버튼이 너무 양 옆일 때 해결 방법: Column에 padding 적절히 주기. 안 주면 너무 양옆에 딱 붙어버림
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF7F77DD)
                    ),
                    enabled = loginState !is UiState.Loading
                ) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "이메일로 로그인",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // 회원가입 이동
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 24.dp),
                    thickness = 1.dp,
                    color = Color.LightGray
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "아직 계정이 없으신가요?",
                        color = Color(0xFF888780),
                        fontSize = 14.sp
                    )
                    TextButton(
                        onClick = onNavigateToSignUp
                    ) {
                        Text(
                            text = "회원가입",
                            color = Color(0xFF534AB7),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }


            } else {
                // 이메일 로그인 폼

                Text(
                    text = "이메일로 로그인",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF534AB7),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 20.dp)
                )
                // 이메일 입력 칸
                // 버튼을 누르면 로그인 버튼이 안없어 지고 아래에 입력칸이 생기는게 더 좋을 것 같은데
                // 방법 찾아보기
                OutlinedTextField(
                    value = email,
                    onValueChange = {email = it},
                    label = {Text("이메일")},
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF7F77DD),
                        unfocusedBorderColor = Color(0xFFCECBF6),
                        focusedLabelColor = Color(0xFF7F77DD),
                        cursorColor = Color(0xFF7F77DD)
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                // 비밀번호 입력 칸
                OutlinedTextField(
                    value = password,
                    onValueChange = {password = it},
                    label = {Text("비밀번호")},
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF7F77DD),
                        unfocusedBorderColor = Color(0xFFCECBF6),
                        focusedLabelColor = Color(0xFF7F77DD),
                        cursorColor = Color(0xFF7F77DD)
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    )
                )

                // 에러 메시지
                if (loginState is UiState.Error) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = (loginState as UiState.Error).message,
                        color = Color(0xFFE24B4A),
                        fontSize = 13.sp,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }


                Spacer(modifier = Modifier.height(20.dp))

                // 로그인 버튼
                Button(
                    onClick = {
                        viewModel.login(email, password)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF7F77DD),
                        disabledContainerColor = Color(0xFFCECBF6)
                    ),
                    enabled = loginState !is UiState.Loading
                ) {
                    if(loginState is UiState.Loading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 2.dp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("로그인 중...", color = Color.White)
                    } else {
                        Text(
                            "로그인",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // 만약 잘못 눌렀을 경우 돌아가는 버튼도 필요할 듯!
                OutlinedButton(
                    onClick = { isEmailLoginVisible = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.5.dp, Color(0xFFCECBF6))
                ) {
                    Text(
                        "취소",
                        color = Color(0xFF7F77DD),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
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