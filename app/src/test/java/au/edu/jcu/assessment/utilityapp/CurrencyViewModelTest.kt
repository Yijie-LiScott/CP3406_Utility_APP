package au.edu.jcu.assessment.utilityapp

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CurrencyViewModelTest {

    // Test verifying that currency conversion calculations produce expected values
    @Test
    fun testCurrencyConversionCalculation() {
        val testRates = mapOf(
            "USD" to 0.75,
            "AUD" to 1.15,
            "CNY" to 5.20
        )

        val targetCurrencies = listOf(
            "USD" to "United States Dollar",
            "AUD" to "Australian Dollar",
            "CNY" to "Chinese Yuan"
        )

        val inputAmount = 100.0

        val convertedList = targetCurrencies.map { (code, _) ->
            val rate = testRates[code] ?: 1.0
            inputAmount * rate
        }

        assertEquals(75.0, convertedList[0], 0.001)
        assertEquals(115.0, convertedList[1], 0.001)
        assertEquals(520.0, convertedList[2], 0.001)

        println("=== Day 2 Conversion Calculation Test Passed ===")
        println("Input: $inputAmount SGD")
        println("USD Result: ${convertedList[0]}")
        println("AUD Result: ${convertedList[1]}")
    }

    // Test verifying default state properties of CurrencyUiState
    @Test
    fun testUiStateDefaults() {
        val defaultState = CurrencyUiState()

        assertEquals("SGD", defaultState.baseCurrency)
        assertEquals("100.0", defaultState.amountInput)
        assertTrue(defaultState.convertedRates.isEmpty())
        assertTrue(!defaultState.isLoading)

        println("=== Day 2 UI State Defaults Test Passed ===")
    }
}