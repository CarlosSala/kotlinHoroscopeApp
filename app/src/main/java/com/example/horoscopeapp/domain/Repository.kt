package com.example.horoscopeapp.domain

import com.example.horoscopeapp.domain.model.PredictionModel

// communication between layer of data and domain
interface Repository {

    suspend fun getPrediction(sign: String): PredictionModel?
}