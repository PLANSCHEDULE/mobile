package com.example.thirdpj.ui.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirdpj.R

@Composable
fun PostTemplateAuthorSection(
    authorHandle: String,
    favoriteCount: Int,
    downloadCount: Int,
    isFavorite: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        // 작성자
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = Color(0xFFAFA9EC)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = authorHandle.replace("@", "").take(1).uppercase(),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = authorHandle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF534AB7)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(thickness = 0.5.dp)
        Spacer(modifier = Modifier.height(16.dp))

        // 통계
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    tint = if (isFavorite) Color(0xFFFF6B6B) else Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
                Text(text = favoriteCount.toString(), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = "하트", fontSize = 12.sp, color = Color(0xFFAFA9EC))
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = R.drawable.download),
                    contentDescription = null,
                    tint = Color(0xFFAFA9EC),
                    modifier = Modifier.size(24.dp)
                )
                Text(text = downloadCount.toString(), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = "다운로드", fontSize = 12.sp, color = Color(0xFFAFA9EC))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(thickness = 0.5.dp)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "일정 미리보기", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF534AB7))
        Spacer(modifier = Modifier.height(8.dp))
    }
}