package au.edu.jcu.assessment.utilityapp.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRateResponse(
    @SerialName("result")
    val result: String,

    @SerialName("base_code")
    val baseCode: String,

    @SerialName("time_last_update_utc")
    val timeLastUpdate: String,

    @SerialName("rates")
    val rates: Map<String, Double>
)

data class ConvertedRate(
    val currencyCode: String,
    val currencyName: String,
    val convertedAmount: Double,
    val exchangeRate: Double
)