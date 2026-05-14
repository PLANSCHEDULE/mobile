package com.example.thirdpj.ui.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirdpj.R
import com.example.thirdpj.ui.theme.ThirdPJTheme


@Composable
fun DetailStats(
    modifier: Modifier = Modifier,
    heartCount: String,
    downloadCount: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // 하트 수 영역
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "찜",
                    tint = Color(0xFFFF5252),
                    modifier = Modifier.size(22.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = heartCount,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )


            }
            Text(
                text = "찜",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

        }
        VerticalDivider(
            modifier = Modifier.height(30.dp),
            color = Color(0xFFE0E0E0),
            thickness = 1.dp
        )

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(painter = painterResource(id = R.drawable.download),
                    contentDescription = "다운로드",
                    tint = Color(0xFF4CAF50),
                    modifier = Modifier.size(22.dp))

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = downloadCount,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "다운로드",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }


    }

}

@Preview(showBackground = true)
@Composable
fun DetailStatsPreview() {
    ThirdPJTheme{
        DetailStats(
            heartCount = "1234",
            downloadCount = "123"
        )
    }
}