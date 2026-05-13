package com.example.thirdpj.ui.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun ProfileForm(
    handle: String,
    onHandleChange: (String) -> Unit,
    isHandleEditable: Boolean = true,
    nickname: String,
    onNicknameChange: (String) -> Unit,
    bio: String,
    onBioChange: (String) -> Unit
) {
    Column(
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text("핸들", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = handle,
            onValueChange = onHandleChange,
            modifier = Modifier.fillMaxWidth(),
            enabled = isHandleEditable,
            placeholder = {Text("unique_id")},
            prefix = {Text("@")},
            singleLine = true,
            // 오 keyboardoptions으로 영문 키보드가 우선으로 보이게 할 수 있는 옵션을 걸 수 있음
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii, imeAction = ImeAction.Next),
            shape = MaterialTheme.shapes.medium

        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("닉네임", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = nickname,
            onValueChange = onNicknameChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {Text("닉네임")},
            singleLine = true,
            // supportingText를 활용하면 글자 수를 띄울 수 있음!
            supportingText = {
                Text(
                    text = "${nickname.length}/20",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            shape = MaterialTheme.shapes.medium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "자기소개",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = bio,
            onValueChange = onBioChange,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 120.dp),
            placeholder = {Text("자신을 소개해 주세요(선택사항)")},
            supportingText = {
                Text(
                    text = "${bio.length}/150",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End
                )
            },

            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            shape = MaterialTheme.shapes.medium
        )
    }

}

@Preview
@Composable
fun ProfileFormPreview() {
    ThirdPJTheme {
        ProfileForm(
            handle = "preview_id",
            onHandleChange = {},
            nickname = "홍길동",
            onNicknameChange = {},
            bio = "안녕하세요! 반갑습니다.",
            onBioChange = {}
        )
    }
}