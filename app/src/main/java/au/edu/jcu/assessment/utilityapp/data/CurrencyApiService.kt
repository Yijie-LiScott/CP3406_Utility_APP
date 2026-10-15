package au.edu.jcu.assessment.utilityapp.data


import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApiService {

    @GET("v6/latest/{base}")
    suspend fun getLatestRates(
        @Path("base") baseCurrency: String
    ): Response<ExchangeRateResponse>

    companion object {
        private const val BASE_URL = "https://open.er-api.com/"

        fun create(): CurrencyApiService {
            val json = Json { ignoreUnknownKeys = true}
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
                .create(CurrencyApiService::class.java)
        }
    }
}

