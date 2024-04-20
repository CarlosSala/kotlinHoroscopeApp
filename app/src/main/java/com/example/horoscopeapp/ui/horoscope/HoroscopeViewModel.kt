package com.example.horoscopeapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.example.horoscopeapp.data.providers.HoroscopeProvider
import com.example.horoscopeapp.domain.model.HoroscopeInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HoroscopeViewModel @Inject constructor(horoscopeProvider: HoroscopeProvider) :
    ViewModel() {

    // good practice, this is used since into class
    private var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())
    // this will be used since out class
    val horoscope: StateFlow<List<HoroscopeInfo>> = _horoscope

    init {

        _horoscope.value = horoscopeProvider.getHoroscope()

        /*        _horoscope.value = listOf(

                    Aries, Taurus, Gemini )*/
    }
}