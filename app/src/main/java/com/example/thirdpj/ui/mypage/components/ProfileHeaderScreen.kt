package com.example.thirdpj.ui.mypage.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun ProfileHeaderScreen(
    nickname: String = "",
    handle: String = "",
    bio: String = "",
    onLogoutClick: () -> Unit = {},
    onEditProfileClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF534AB7))
                .padding(horizontal = 24.dp)
        ) {
            Row(
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                // 프로필 수정 버튼
                TextButton(onClick = {
                    Log.d("PROFILE_EDIT", "ProfileHeaderScreen 버튼 클릭됨")
                    onEditProfileClick()
                }) {
                    Text(
                        text = "프로필 수정",
                        color = Color(0xFFCECBF6),
                        fontSize = 12.sp
                    )
                }
                // 로그아웃 버튼 추가
                TextButton(
                    onClick = onLogoutClick
                ) {
                    Text(
                        text = "로그아웃",
                        color = Color(0xFFCECBF6),
                        fontSize = 12.sp
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(top = 40.dp, bottom = 60.dp)
            ) {
                // 프로필 이미지 영역
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFAFA9EC))
                        .border(2.dp, Color.White.copy(alpha = 0.5f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = nickname.take(1),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                }

                Spacer(modifier = Modifier.height(25.dp))

                // 이름
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = nickname,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                // 문구
                Text(
                    text = "@$handle * $bio ✨",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.9f),
                    modifier = Modifier.padding(top = 4.dp)

                )
            }
        }
        // 통계 정보 카드
        Card(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                // 상단에 겹치려면 offset 필요!
                .offset(y = (-20).dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                // 아래 부분은 못없애려나..
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 통계 부분 db 연결 후에 구현하는게 좋을 것 같음



            }
        }
    }
}

@Preview
@Composable
fun ProfileHeaderPreview() {
    ThirdPJTheme {
        ProfileHeaderScreen()
    }
}