package com.example.horoscopeapp

import com.example.horoscopeapp.data.providers.network.response.PredictionResponse
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Aquarius
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Aries
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Cancer
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Capricorn
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Gemini
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Leo
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Libra
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Pisces
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Sagittarius
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Scorpio
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Taurus
import com.example.horoscopeapp.domain.model.HoroscopeInfo.Virgo

object HoroscopeMotherObject {

    val anyResponse = PredictionResponse("date", "prediction", "geminis")

    val horoscopeInfoList = listOf(
        Aries,
        Taurus,
        Gemini,
        Cancer,
        Leo,
        Virgo,
        Libra,
        Scorpio,
        Sagittarius,
        Capricorn,
        Aquarius,
        Pisces
    )
}