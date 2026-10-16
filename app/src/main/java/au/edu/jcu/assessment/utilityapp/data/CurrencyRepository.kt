package au.edu.jcu.assessment.utilityapp.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CurrencyRepository(
    private val apiService: CurrencyApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun fetchRates(baseCurrency: String): NetworkResult<ExchangeRateResponse> {
        return withContext(dispatcher) {
            try {
                val response = apiService.getLatestRates(baseCurrency)
                if (response.isSuccessful && response.body() != null) {
                    NetworkResult.Success(response.body()!!)
                } else {
                    NetworkResult.Error("Failed to fetch rates: ${response.code()}")
                }
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown network error")
            }
        }
    }
}