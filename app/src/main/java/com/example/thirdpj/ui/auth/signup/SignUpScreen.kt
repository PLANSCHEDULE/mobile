package com.example.thirdpj.ui.auth.signup

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirdpj.R
import com.example.thirdpj.data.auth.dto.SignUpRequest
import com.example.thirdpj.data.auth.network.RetrofitClient
import com.example.thirdpj.ui.theme.ThirdPJTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(onBack: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    // 비동기 통신용
    val coroutineScope = rememberCoroutineScope()
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

            // 닉네임 입력창
            OutlinedTextField(
                value = name,
                onValueChange = {name = it},
                label = {Text("이름")},
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 회원가입 완료 버튼
            Button(
                onClick = {
                    coroutineScope.launch(Dispatchers.IO) {
                        try {
                            val request = SignUpRequest(name, email, password)

                            val response = RetrofitClient.api.signUp(request)

                            if(response.isSuccessful) {
                                withContext(Dispatchers.Main) {
                                    onBack()
                                }
                            } else {
                                Log.e("SignUp","서버 에러: ${response.errorBody()?.string()}")
                            }
                        } catch (e: Exception) {
                            Log.e("SignUp","통신 실패: ${e.message}")
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("회원가입 하기")
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