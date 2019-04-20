package br.com.andersonsv.test.extension

import org.junit.Assert.assertEquals
import org.junit.Test

class NumericExtensionTest {
    @Test
    fun price_without_fraction() {
        val price = 40.0
        val priceCheck = price.asBRLNoFraction(false)
        val expected = "40"

        assertEquals(expected, priceCheck)
    }

    @Test
    fun price_with_fraction() {
        val price = 40.0
        val priceCheck = price.asBRL(false)
        val expected = "40,00"

        assertEquals(expected, priceCheck)
    }

    @Test
    fun price_with_fraction_and_currency() {
        val price = 40.0
        val priceCheck = price.asBRL(true)
        val expected = "R$ 40,00"

        assertEquals(expected, priceCheck)
    }

    @Test
    fun price_without_fraction_with_currency() {
        val price = 40.0
        val priceCheck = price.asBRLNoFraction(true)
        val expected = "R$ 40"

        assertEquals(expected, priceCheck)
    }
}
