package com.example.thirdpj.ui.detail.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thirdpj.ui.detail.PostTemplateDetailViewModel
import com.example.thirdpj.ui.detail.components.PostTemplateAuthorSection
import com.example.thirdpj.ui.detail.components.PostTemplateBottomBar
import com.example.thirdpj.ui.detail.components.PostTemplateDetailHeader
import com.example.thirdpj.ui.detail.components.PostTemplateDetailTopBar
import com.example.thirdpj.ui.detail.components.PostTemplateItemRow

@Composable
fun PostTemplateDetailScreen(
    postTemplateId: Long,
    onBackClick: () -> Unit,
    viewModel: PostTemplateDetailViewModel = viewModel()
) {
    val template by viewModel.template.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(postTemplateId) {
        viewModel.fetchDetail(postTemplateId)
    }

    Scaffold(
        topBar = {
            PostTemplateDetailTopBar(onBackClick = onBackClick)
        },
        bottomBar = {
            template?.let { t ->
                PostTemplateBottomBar(
                    isFavorite = t.isFavorite,
                    onFavoriteClick = { viewModel.toggleFavorite(t.postTemplateId) },
                    onForkClick = {
                        viewModel.downloadTemplate(
                            postTemplateId = t.postTemplateId,
                            onSuccess = {
                                Toast.makeText(context, "내 일정으로 포크했어요!", Toast.LENGTH_SHORT).show()
                            },
                            onError = { msg ->
                                Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                            }
                        )
                    }
                )
            }
        },
        containerColor = Color(0xFFF5F3FF)
    ) { innerPadding ->
        if (template == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFF7F77DD))
            }
        } else {
            val t = template!!
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                item {
                    PostTemplateDetailHeader(
                        title = t.title,
                        background = t.background
                    )
                }

                item {
                    PostTemplateAuthorSection(
                        authorHandle = t.authorHandle,
                        favoriteCount = t.favoriteCount,
                        downloadCount = t.downloadCount,
                        isFavorite = t.isFavorite
                    )
                }

                items(
                    t.items.sortedBy { it.sequence },
                    key = { it.sequence }
                ) { item ->
                    PostTemplateItemRow(
                        itemTime = item.itemTime,
                        content = item.content
                    )
                }

                item { Spacer(modifier = Modifier.height(30.dp)) }
            }
        }
    }
}