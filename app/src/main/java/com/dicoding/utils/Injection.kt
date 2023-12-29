package com.dicoding.utils

import android.content.Context
import com.dicoding.api.ApiConfig
import com.dicoding.repository.Repository

object Injection {
    fun provideRepository(context: Context) : Repository {
        val apiService = ApiConfig.getApiService()
        return Repository(apiService)
    }
}