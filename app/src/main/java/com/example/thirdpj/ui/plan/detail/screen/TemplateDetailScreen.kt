package com.example.thirdpj.ui.plan.detail.screen

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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
    viewModel: TemplateDetailViewModel = viewModel()
) {
    val template by viewModel.template.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(templateId) {
        viewModel.fetchTemplateDetail(templateId)
    }

    Scaffold(
        topBar = {
            DetailPlanTopBar(
                onBackClick = onBackClick,
                onEditClick = { template?.let { onEditClick(it.id) } },
                onDeleteClick = {
                    template?.let {
                        viewModel.deleteTemplate(
                            templateId = it.id,
                            onSuccess = {
                                Toast.makeText(context, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
                                onBackClick()
                            },
                            onError = { msg ->
                                Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                            }
                        )
                    }

                }
            )
        },
        containerColor = Color(0xFFFFFBF5)
    ) { innerPadding ->
        if (template == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFF7F77DD))
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
                            .background(Color(0xFFF5F3FF))
                            .padding(vertical = 24.dp)
                    ) {
                        Text(
                            text = template?.title ?: "",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF534AB7)
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(Color(0xFFEEECFB), RoundedCornerShape(8.dp))
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp),
                                tint = Color(0xFF7F77DD)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = template?.targetDate ?: "",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF534AB7)
                            )
                        }
                    }
                }

                // 아이템 목록
                items(
                    template?.items?.sortedBy { it.sequence } ?: emptyList(),
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