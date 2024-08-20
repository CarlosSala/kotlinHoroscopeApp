package com.example.horoscopeapp.ui.horoscope

import com.example.horoscopeapp.HoroscopeMotherObject.horoscopeInfoList
import com.example.horoscopeapp.data.providers.HoroscopeProvider
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class HoroscopeViewModelTest {

    @MockK
    lateinit var horoscopeProvider: HoroscopeProvider

    private lateinit var horoscopeViewModel: HoroscopeViewModel

    @Before
    fun setUp() {

        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `when viewModel is created then horoscopes are load`() {

        // given
        every { horoscopeProvider.getHoroscope() } returns horoscopeInfoList

        horoscopeViewModel = HoroscopeViewModel(horoscopeProvider)

        // when
        val horoscope = horoscopeViewModel.horoscope.value

        // then
        assert(horoscope.isNotEmpty())
    }
}