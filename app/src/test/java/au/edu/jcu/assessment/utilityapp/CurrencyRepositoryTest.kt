package au.edu.jcu.assessment.utilityapp

import au.edu.jcu.assessment.utilityapp.data.CurrencyApiService
import au.edu.jcu.assessment.utilityapp.data.CurrencyRepository
import au.edu.jcu.assessment.utilityapp.data.NetworkResult
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CurrencyRepositoryTest {

    // Test verifying that the repository successfully fetches exchange rates for base currency SGD
    @Test
    fun testFetchRatesSuccess() = runBlocking {
        val apiService = CurrencyApiService.create()
        val repository = CurrencyRepository(apiService = apiService)
        val result = repository.fetchRates("SGD")

        // Assert network call returned NetworkResult.Success
        assertTrue("Repository call should succeed", result is NetworkResult.Success)

        val response = (result as NetworkResult.Success).data
        assertNotNull("Response body should not be null", response)
        assertEquals("Base currency code should match requested base", "SGD", response.baseCode)

        // Assert rates map contains essential target currencies
        val rates = response.rates
        assertNotNull("Rates map should exist", rates)
        assertTrue("Rates should contain USD", rates.containsKey("USD"))
        assertTrue("Rates should contain AUD", rates.containsKey("AUD"))

        // Print sample output to test console for manual inspection
        println("=== Day 1 API Test Passed ===")
        println("Base Currency: ${response.baseCode}")
        println("Last Updated: ${response.timeLastUpdate}")
        println("SGD to USD Rate: ${rates["USD"]}")
        println("SGD to AUD Rate: ${rates["AUD"]}")
    }
}