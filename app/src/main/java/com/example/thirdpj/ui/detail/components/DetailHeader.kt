package com.example.thirdpj.ui.detail.components

import android.text.Layout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirdpj.R

@Composable
fun DetailHeader(
    onBackClick: () -> Unit,
    title: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .background(Color(0xFFFE964A))
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.2f)
                        )
                    )

                )
        )

        // 뒤로 가기 버튼
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .padding(top = 16.dp, start = 8.dp)
                .align(Alignment.TopStart)
        ) {
            Icon(painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "뒤로가기",
                tint = Color.White)
        }

        Text(
            text = title,
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 36.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, bottom = 30.dp)
        )

    }

}

@Preview
@Composable
fun DetailHeaderPreview() {
    DetailHeader(
        onBackClick = {},
        title =  "일본 당일치기\n도쿄 코스"
    )
}