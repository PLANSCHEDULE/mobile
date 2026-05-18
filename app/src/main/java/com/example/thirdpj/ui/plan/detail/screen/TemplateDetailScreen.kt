package com.example.thirdpj.ui.plan.detail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thirdpj.ui.plan.detail.TemplateDetailViewModel
import com.example.thirdpj.ui.plan.detail.components.DetailPlanItem
import com.example.thirdpj.ui.plan.detail.components.DetailPlanTopBar

@Composable
fun TemplateDetailScreen(
    templateId: Long,
    onBackClick: () -> Unit,
    onEditClick: (Long) -> Unit = {},
    onDeleteClick: (Long) -> Unit = {},
    viewModel: TemplateDetailViewModel = viewModel()
) {
    val template by viewModel.template.collectAsStateWithLifecycle()

    LaunchedEffect(templateId) {
        viewModel.fetchTemplateDetail(templateId)
    }

    Scaffold(
        topBar = {
            DetailPlanTopBar(
                onBackClick = onBackClick,
                onEditClick = { template?.let { onEditClick(it.id) } },
                onDeleteClick = { template?.let { onDeleteClick(it.id) } }
            )
        },
        containerColor = Color(0xFFFFFBF5)
    ) { innerPadding ->
        if (template == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFFEF7F61))
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                // 헤더 영역
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFFFFBF5))
                            .padding(vertical = 24.dp)
                    ) {
                        Text(
                            text = template!!.title,
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(Color(0xFFF8F0E5), RoundedCornerShape(8.dp))
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = Color(0xFFF27A54)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = template!!.targetDate,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }

                // 아이템 목록
                items(
                    template!!.items.sortedBy { it.sequence },
                    key = { it.id }
                ) { item ->
                    DetailPlanItem(
                        itemTime = item.itemTime,
                        content = item.content,
                        isCompleted = item.isCompleted
                    )
                }

                item { Spacer(modifier = Modifier.height(30.dp)) }
            }
        }
    }
}