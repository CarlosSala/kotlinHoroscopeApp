package com.example.horoscopeapp.data.providers.network.response

import com.example.horoscopeapp.HoroscopeMotherObject
import io.kotlintest.shouldBe
import org.junit.Test


class PredictionResponseTest {

    @Test
    fun `toDomain should return a correct predictionModel`() {

        // Given
        val horoscopeResponse = HoroscopeMotherObject.anyResponse.copy(sign = "taurus")

        // When
        val predictionModel = horoscopeResponse.toDomain()

        // Then
        predictionModel.sign shouldBe horoscopeResponse.sign
        predictionModel.horoscope shouldBe horoscopeResponse.horoscope
    }
}