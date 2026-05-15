package com.example.thirdpj.ui.auth.signup


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.thirdpj.R
import com.example.thirdpj.util.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(viewModel: SignUpViewModel,
                 onBack: () -> Unit,
                 onSignUpSuccess: () -> Unit) {

    val signUpState by viewModel.signUpState.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    LaunchedEffect(signUpState) {
        if(signUpState is UiState.Success) {
            onSignUpSuccess()
        }
    }
    // 상단바, Scaffold
    Scaffold(
        // 화면 상단바를 만들기 위해서 Scaffold의 Topbar 사용
        topBar = {
            // 회원가입 페이지니까 우선 제목 가운데로 정렬
            // 위에 OptIn 안써주면 아래 내용 에러남
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "회원가입")
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(painter = painterResource(id = R.drawable.arrow_back),
                            contentDescription = "뒤로가기")
                    }
                },
                // 상단바 구분이 안가서 우선 색상 아무거나
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
                .fillMaxSize()
        ) {

            // 이름 입력창
            OutlinedTextField(
                value = name,
                onValueChange = {name = it},
                label = {Text("이름")},
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 이메일 입력창
           OutlinedTextField(
               value = email,
               onValueChange = {email = it},
               label = {Text("이메일")},
               placeholder = {Text("example@gmail.com")},
               modifier = Modifier.fillMaxWidth(),
               keyboardOptions = KeyboardOptions(
                   keyboardType = KeyboardType.Email,
                   imeAction = ImeAction.Next
               )
           )
            Spacer(modifier = Modifier.height(16.dp) )

            // 비밀번호 입력창
            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                label = {Text("비밀번호")},
                modifier = Modifier.fillMaxWidth(),
                // visualTransformation로 비밀번호 입력 시 비밀번호 가리기 가능!
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                )
            )



            Spacer(modifier = Modifier.height(16.dp))

            // 회원가입 완료 버튼
            Button(
                onClick = {
                    viewModel.signUp(name, email, password)
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = signUpState !is UiState.Loading
            ) {
                if(signUpState is UiState.Loading) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("가입 처리 중...")
                } else {
                    Text("회원가입 완료")
                }
            }

            // 에러 메시지 표시
            if(signUpState is UiState.Error) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = (signUpState as UiState.Error).message,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

        }
    }
}

//@Preview
//@Composable
//fun SignUpScreenPreview() {
//    ThirdPJTheme{
//        SignUpScreen()
//    }
//}