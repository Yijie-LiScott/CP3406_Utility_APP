package au.edu.jcu.assessment.utilityapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.edu.jcu.assessment.utilityapp.data.ConvertedRate
import au.edu.jcu.assessment.utilityapp.data.CurrencyRepository
import au.edu.jcu.assessment.utilityapp.data.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// UI state holder representing the screen data
data class CurrencyUiState(
    val isLoading: Boolean = false,
    val baseCurrency: String = "SGD",
    val amountInput: String = "100.0",
    val lastUpdated: String = "",
    val convertedRates: List<ConvertedRate> = emptyList(),
    val errorMessage: String? = null
)

// ViewModel responsible for handling conversion business logic and repository interaction
class CurrencyViewModel(
    private val repository: CurrencyRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CurrencyUiState())
    val uiState: StateFlow<CurrencyUiState> = _uiState.asStateFlow()

    // Cached map of rates for the currently loaded base currency
    private var cachedRates: Map<String, Double> = emptyMap()

    // Common destination currencies to display at-a-glance
    private val targetCurrencies = listOf(
        "USD" to "United States Dollar",
        "AUD" to "Australian Dollar",
        "CNY" to "Chinese Yuan",
        "EUR" to "Euro",
        "JPY" to "Japanese Yen",
        "GBP" to "British Pound",
        "MYR" to "Malaysian Ringgit"
    )

    init {
        loadRates(_uiState.value.baseCurrency)
    }

    // Fetches fresh exchange rates using coroutines via the repository
    fun loadRates(base: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            when (val result = repository.fetchRates(base)) {
                is NetworkResult.Success -> {
                    val response = result.data
                    cachedRates = response.rates
                    val parsedAmount = _uiState.value.amountInput.toDoubleOrNull() ?: 0.0
                    val converted = calculateConversions(parsedAmount, cachedRates)

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            baseCurrency = response.baseCode,
                            lastUpdated = response.timeLastUpdate,
                            convertedRates = converted,
                            errorMessage = null
                        )
                    }
                }
                is NetworkResult.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.error
                        )
                    }
                }
            }
        }
    }

    // Triggered when user inputs a new amount in the text field
    fun onAmountChanged(newAmount: String) {
        _uiState.update { it.copy(amountInput = newAmount) }
        val parsedAmount = newAmount.toDoubleOrNull() ?: 0.0
        val updatedList = calculateConversions(parsedAmount, cachedRates)
        _uiState.update { it.copy(convertedRates = updatedList) }
    }

    // Triggered when user switches the base currency in the settings tab
    fun onBaseCurrencyChanged(newBase: String) {
        if (_uiState.value.baseCurrency != newBase) {
            _uiState.update { it.copy(baseCurrency = newBase) }
            loadRates(newBase)
        }
    }

    // Helper function to transform rates into display items
    private fun calculateConversions(amount: Double, rates: Map<String, Double>): List<ConvertedRate> {
        if (rates.isEmpty()) return emptyList()

        return targetCurrencies.map { (code, name) ->
            val rate = rates[code] ?: 1.0
            ConvertedRate(
                currencyCode = code,
                currencyName = name,
                convertedAmount = amount * rate,
                exchangeRate = rate
            )
        }
    }
}