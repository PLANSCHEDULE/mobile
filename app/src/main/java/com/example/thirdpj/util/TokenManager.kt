package com.example.thirdpj.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "auth_prefs")

class TokenManager(private val context: Context) {

    companion object {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
    }

    // 토큰 가져오기
    fun getAccessToken(): Flow<String?> {
        return context.dataStore.data.map { prefs -> prefs[ACCESS_TOKEN_KEY] }
    }

    fun getRefreshToken(): Flow<String?> {
        return context.dataStore.data.map { prefs -> prefs[REFRESH_TOKEN_KEY] }
    }

    // 토큰 저장하기
    suspend fun saveTokens(accessToken: String, refreshToken: String) {
        context.dataStore.edit { prefs ->
            prefs[ACCESS_TOKEN_KEY] = accessToken
            prefs[REFRESH_TOKEN_KEY] = refreshToken
        }
    }

    // 로그아웃 시 토큰 삭제
    // .clear을 호출하면 DataStore 파일 안에 저장된 모든 데이터를 비움
    suspend fun clearTokens() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}