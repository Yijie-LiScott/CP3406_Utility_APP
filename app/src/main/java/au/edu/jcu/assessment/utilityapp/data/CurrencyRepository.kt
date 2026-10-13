package au.edu.jcu.assessment.utilityapp.data

//Repository pattern to decouple API networking from business logic
class CurrencyRepository(
    private val apiService: CurrencyApiService = CurrencyApiService.create()
) {
    //Fetch live currency rates and wrap the result safely
    suspend fun fetchRates(baseCurrency: String): Result<ExchangeRateResponse> {
        return try {
            val response = apiService.getLatestRates(baseCurrency)
            Result.success(response)
        } catch (e:Exception){
            Result.failure(e)
        }
    }
}