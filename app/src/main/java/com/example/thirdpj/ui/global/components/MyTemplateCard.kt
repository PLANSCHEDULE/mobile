package com.example.thirdpj.ui.global.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirdpj.data.template.dto.TemplateResponse
import androidx.core.graphics.toColorInt


@Composable
fun MyTemplateCard(
    template: TemplateResponse,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .width(160.dp)
            .clickable{onClick()},
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    try {
                        Color(template.background.toColorInt())
                    } catch (e: Exception) {
                        Color(0xFFFFB380)
                    }
                )
        )


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .defaultMinSize(minHeight = 100.dp)
        ) {
            Text(
                text = template.title,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(6.dp))

            HorizontalDivider(thickness = 0.5.dp)

            Spacer(modifier = Modifier.height(6.dp))

            template.items.sortedBy { it.sequence }.forEach { item ->
                Row(modifier = Modifier.padding(vertical = 1.dp)) {
                    Text(
                        text = item.itemTime,
                        fontSize = 10.sp,
                        color = Color.Gray,
                        modifier = Modifier.width(35.dp)
                    )
                    Text(
                        text = "•",
                        fontSize = 10.sp,
                        color = Color.LightGray,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                    Text(
                        text = item.content,
                        fontSize = 10.sp,
                        color = Color.DarkGray,
                        maxLines = 1
                    )
                }
            }
        }

    }
}