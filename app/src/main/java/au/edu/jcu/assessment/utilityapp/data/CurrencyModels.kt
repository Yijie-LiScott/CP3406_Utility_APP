package au.edu.jcu.assessment.utilityapp.data

import com.google.gson.annotations.SerializedName

//Response model matching open exchange rate API schema
data class ExchangeRateResponse(
    @SerializedName("result")
    val result: String,

    @SerializedName("base_code")
    val baseCode: String,

    @SerializedName("time_last_update_utc")
    val timeLastUpdate: String,

    @SerializedName("rates")
    val rates: Map<String, Double>
)

// UI data model representing a single converted currency item
data class ConvertedRate(
    val currencyCode: String,
    val currencyName: String,
    val convertedAmount: Double,
    val exchangeRate: Double
)