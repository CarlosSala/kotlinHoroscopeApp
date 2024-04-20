package com.example.horoscopeapp.data.providers

import android.util.Log
import com.example.horoscopeapp.data.providers.network.HoroscopeApiService
import com.example.horoscopeapp.domain.Repository
import com.example.horoscopeapp.domain.model.PredictionModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService) : Repository {
    override suspend fun getPrediction(sign: String): PredictionModel? {

        // example call retrofit

        runCatching { apiService.getHoroscope(sign) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("test", "there is a error ${it.message}") }

        return null
    }
}