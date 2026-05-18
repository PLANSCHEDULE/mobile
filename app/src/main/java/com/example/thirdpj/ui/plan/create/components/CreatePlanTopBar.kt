package com.example.thirdpj.ui.plan.create.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirdpj.R
import com.example.thirdpj.ui.theme.ThirdPJTheme

//
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePlanTopBar(
    onBackClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
    onShareClick: () -> Unit = {}
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        ),
        // 뒤로 가기 버튼
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "뒤로가기",
                )
            }
        },
        // 작성 중 표시
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("✏️ ", fontSize = 14.sp)
                Text("작성 중",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFFF27A54))
            }
        },
        //버튼 영역
        actions = {
            // 저장 버튼
            TextButton(onClick = onSaveClick) {
                Text("저장", color = Color.Black, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.width(4.dp))

            // 공유 버튼
            Button(
                onClick = onShareClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF27A54)),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.share),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                    )
                Spacer(modifier = Modifier.width(4.dp))
                Text("공유", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

    )

}

@Preview
@Composable
fun CreatePlanTopBarPreview() {
    ThirdPJTheme {
        CreatePlanTopBar()
    }
}