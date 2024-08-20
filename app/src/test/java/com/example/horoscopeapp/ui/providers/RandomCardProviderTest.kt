package com.example.horoscopeapp.ui.providers

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class RandomCardProviderTest {

    @Test
    fun `getRandomCard should return a random card`() {

        // given
        val randomCard = RandomCardProvider()

        // when
        val card = randomCard.getLucky()

        // then
        assertNotNull(card)
    }
}