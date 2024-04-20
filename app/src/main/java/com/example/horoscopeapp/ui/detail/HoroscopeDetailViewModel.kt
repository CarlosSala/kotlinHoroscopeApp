package com.example.horoscopeapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscopeapp.domain.model.HoroscopeModel
import com.example.horoscopeapp.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(private val getPredictionUseCase: GetPredictionUseCase) :
    ViewModel() {


    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading)
    val state: StateFlow<HoroscopeDetailState> = _state

    lateinit var horoscope: HoroscopeModel


    fun getHoroscope(sign: HoroscopeModel) {
        viewModelScope.launch {

            horoscope = sign

            // main thread
            _state.value = HoroscopeDetailState.Loading

            // secondary thread
            val result = withContext(Dispatchers.IO) {
                getPredictionUseCase(sign.name)
            }
            if (result != null) {
                _state.value =
                    HoroscopeDetailState.Success(result.horoscope, result.sign, horoscope)
            } else {
                _state.value = HoroscopeDetailState.Error("error has occurred")
            }
        }

        // main thread
    }

}