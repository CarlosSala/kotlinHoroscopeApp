package com.example.horoscopeapp.ui.detail

import com.example.horoscopeapp.domain.model.HoroscopeModel

sealed class HoroscopeDetailState {

    data object Loading : HoroscopeDetailState()

    // when class require parameters is data class
    data class Error(val error: String) : HoroscopeDetailState()
    data class Success(
        val prediction: String, val sign: String, val horoscopeModel: HoroscopeModel
    ) : HoroscopeDetailState()

}