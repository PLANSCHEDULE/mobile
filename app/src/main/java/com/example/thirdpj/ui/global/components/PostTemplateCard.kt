package com.example.thirdpj.ui.global.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.thirdpj.R
import com.example.thirdpj.data.post.dto.PostTemplateDto

@Composable
fun PostTemplateCard(
    template: PostTemplateDto,
    modifier: Modifier = Modifier,
    onDownloadClick: (Long) -> Unit = {},
    onFavoriteClick: (Long) -> Unit = {}
             ) {

    var isFavorite by remember(template.isFavorite) { mutableStateOf(template.isFavorite) }
    var favoriteCount by remember(template.favoriteCount) { mutableIntStateOf(template.favoriteCount) }
    var downloadCount by remember(template.downloadCount) { mutableIntStateOf(template.downloadCount) }

    val displayItems = template.items.sortedBy { it.sequence }.take(2)
    val remainCount = template.items.size - 2
    val hasMore = remainCount > 0


    Card(
        modifier = modifier
            .padding(4.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)


    ) {
        // 상단 영역( 이미지 or 배경)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(
                    try {
                        Color(template.background?.toColorInt() ?: 0xFFFFB380.toInt())
                    } catch (e: Exception) {
                        Color(0xFFFFB380)
                    }
                )
                .padding(10.dp)
        ) {
            // 다운로드 버튼
            Surface(
                onClick = {
                    downloadCount += 1
                    onDownloadClick(template.postTemplateId)
                },
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.TopStart),
                shape = CircleShape
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.download),
                    contentDescription = null,
                    modifier = Modifier.padding(6.dp),
                    tint = Color.DarkGray
                )
            }

            // 찜 버튼
            Surface(
                onClick = {
                    isFavorite = !isFavorite
                    favoriteCount = if (isFavorite) favoriteCount + 1 else favoriteCount - 1
                    onFavoriteClick(template.postTemplateId)
                },
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.TopEnd),
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.8f)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier.padding(6.dp),
                    tint = if (isFavorite) Color(0xFFFF6B6B) else Color.Gray
                )
            }

        }

        // 정보 및 일정 영역
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .height(140.dp)
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
//                .defaultMinSize(minHeight = 140.dp)

        ) {
            // 제목
            Text(
                text = template.title,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                maxLines = 1

            )

            Spacer(modifier = Modifier.height(2.dp))

            // 사용자 정보
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.size(18.dp),
                    shape = CircleShape,
                    color = Color(0xFFAFA9EC)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        val initial = template.authorHandle.replace("@", "").take(1).uppercase()
                        Text(
                            text = initial,
                            color = Color.White,
                            fontSize = 6.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = template.authorHandle,
                    color = Color.Gray,
                    fontSize = 10.sp
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 10.dp),
                thickness = 0.5.dp
            )

            // 일정 내용 구현 자리 필요!
            displayItems.forEach { item ->
                Row(modifier = Modifier.padding(vertical = 1.dp)) {
                    Text(text = item.itemTime,
                        fontSize = 10.sp,
                        color = Color.Gray,
                        modifier = Modifier.width(35.dp))
                    Text(text = "•",
                        fontSize = 10.sp,
                        color = Color.LightGray,
                        modifier = Modifier.padding(horizontal = 4.dp))
                    Text(text = item.content,
                        fontSize = 10.sp,
                        color = Color.DarkGray,
                        maxLines = 1
                    )
                }
            }
            if (hasMore) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "+${remainCount}개 더",
                    fontSize = 10.sp,
                    color = Color.Gray,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

        }

        // 통계 영역 (통계 영역을 나중에 구현하는게 좋으려나. 한 번 생각해 보기)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEEECFB))
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier.size(12.dp),
                tint = if (isFavorite) Color(0xFFFF6B6B) else Color.LightGray
            )
            Text(
                text = favoriteCount.toString(),  // template.favoriteCount → favoriteCount
                fontSize = 12.sp,
                modifier = Modifier.padding(6.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                painter = painterResource(id = R.drawable.download),
                contentDescription = null,
                modifier = Modifier.padding(6.dp),
                tint = Color(0xFFAFA9EC)
            )
            Text(
                text = downloadCount.toString(),  // template.downloadCount → downloadCount
                fontSize = 12.sp,
                modifier = Modifier.padding(6.dp)
            )
        }
    }
}


