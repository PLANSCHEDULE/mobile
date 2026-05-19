package com.example.thirdpj.ui.profile.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.thirdpj.R
import com.example.thirdpj.ui.theme.ThirdPJTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(
    title: String,
    onBackClick: () -> Unit,
    actionText: String,
    onActionClick: () -> Unit,
    isActionEnabled: Boolean = true,
    showBackButton: Boolean = true

) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF534AB7)
            )
        },
        navigationIcon = {
            if(showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = "뒤로가기",
                        tint = Color(0xFF534AB7)
                    )
                }
            }
        },
        actions = {
            TextButton(
                onClick = onActionClick,
                enabled = isActionEnabled
            ) {
                Text(
                    text = actionText,
                    style = MaterialTheme.typography.labelLarge,
                    // MaterialTheme.colorSchema를 통해서 활성화 상태에 따라 색상 강조가 가능!
                    fontWeight = FontWeight.Bold,
                    color = if (isActionEnabled) Color(0xFF7F77DD)
                    else Color(0xFFCECBF6)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFF5F3FF)
        )
    )

}

@Preview(showBackground = true, name = "프로필 생성 모드 (활성)")
@Composable
fun ProfileCreateTopBarPreview() {
    ThirdPJTheme {
        ProfileTopBar(
            title = "프로필 생성",
            onBackClick = {},
            actionText = "완료",
            onActionClick = {},
            isActionEnabled = true
        )
    }
}

@Preview(showBackground = true, name = "프로필 생성 모드 (비활성)")
@Composable
fun ProfileCreateDisabledTopBarPreview() {
    ThirdPJTheme {
        ProfileTopBar(
            title = "프로필 생성",
            onBackClick = {},
            actionText = "완료",
            onActionClick = {},
            isActionEnabled = false
        )
    }
}

@Preview(showBackground = true, name = "프로필 수정 모드")
@Composable
fun ProfileEditTopBarPreview() {
    ThirdPJTheme {
        ProfileTopBar(
            title = "프로필 수정",
            onBackClick = {},
            actionText = "저장",
            onActionClick = {},
            isActionEnabled = true
        )
    }
}


