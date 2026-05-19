package com.example.thirdpj.ui.global.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.thirdpj.ui.theme.ThirdPJTheme

@Composable
fun BottomBar(navController: NavController) {

    // 현재 어떤 화면이 활성화되어 있는 지 관찰하는 변수
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    NavigationBar(
        containerColor = Color(0xFFF5F3FF),
        tonalElevation = 8.dp
    ) {
        // 홈
        NavigationBarItem(
            // 우선 ui 틀 잡는 단계에서는 bolean으로 두는게 좋은 거 같다
            selected = currentRoute == "home",
            onClick = {
                navController.navigate("home") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = false
                }
            },
            icon = { Icon(Icons.Default.Home, contentDescription = "홈",
                tint = if (currentRoute == "home") Color(0xFF534AB7) else Color(0xFFAFA9EC)) },
            label = { Text("HOME", color = if (currentRoute == "home") Color(0xFF534AB7) else Color(0xFFAFA9EC)) }
        )

        // 탐색
        // 음 탐색 기능 대신 전체적인 템플릿을 볼 수 있는 메뉴여도 괜찮을 듯
        NavigationBarItem(
            selected = currentRoute == "search",
            onClick = {
                navController.navigate("search") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = false
                }
            },
            icon = { Icon(Icons.Default.Search, contentDescription = "검색",
                tint = if (currentRoute == "search") Color(0xFF534AB7) else Color(0xFFAFA9EC)) },
            label = { Text("탐색", color = if (currentRoute == "search") Color(0xFF534AB7) else Color(0xFFAFA9EC)) }
        )

        // 찜
        NavigationBarItem(
            selected = currentRoute == "favorite",
            onClick = {
                navController.navigate("favorite") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = false

                }

            },
            icon = { Icon(Icons.Default.Favorite, contentDescription = "찜 목록",
                tint = if (currentRoute == "favorite") Color(0xFF534AB7) else Color(0xFFAFA9EC)) },
            label = { Text("찜", color = if (currentRoute == "favorite") Color(0xFF534AB7) else Color(0xFFAFA9EC)) }
        )

        NavigationBarItem(
            selected = currentRoute == "mypage",
            onClick = {
                navController.navigate("mypage") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = false
                    // 이전걸 복원하려해서 홈에서 찜이 false로 날아오는데 true로 표시하는 경우가 생김
                }
            },
            icon = { Icon(Icons.Default.Person, contentDescription = "마이페이지",
                tint = if (currentRoute == "mypage") Color(0xFF534AB7) else Color(0xFFAFA9EC)) },
            label = { Text("마이페이지",
                color = if (currentRoute == "mypage") Color(0xFF534AB7) else Color(0xFFAFA9EC)) }
        )

    }
}

@Preview
@Composable
fun BottomBarPreview() {
    val navController = rememberNavController()
    ThirdPJTheme {
        BottomBar(navController = navController)
    }
}