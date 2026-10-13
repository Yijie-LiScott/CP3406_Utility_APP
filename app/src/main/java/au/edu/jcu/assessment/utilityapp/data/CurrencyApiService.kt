package au.edu.jcu.assessment.utilityapp.data


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// Retrofit API interface for public exchange rates
interface CurrencyApiService {

    @GET("v6/latest/{base}")
    suspend fun getLatestRates(
        @Path("base") baseCurrency: String
    ): ExchangeRateResponse

    companion object {
        private const val BASE_URL = "https://open.er-api.com/"

        // Factory method to initialize Retrofit instance
        fun create(): CurrencyApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CurrencyApiService::class.java)
        }
    }
}