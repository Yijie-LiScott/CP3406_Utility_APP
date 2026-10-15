# MobileTech Utility App

This is a basic Android app template for **Assessment 1: Utility App**.
It provides the structure for a simple tabular UI using **Jetpack Compose** and **Material Design 3**.

---

## App Purpose & Utility Logic
Unlike generic multi-screen calculators, this app implements a real-world **Currency QuickView** designed for travelers and international students. Following the core "At-a-Glance" utility principle, users enter a base amount and immediately view converted values across multiple major foreign currencies simultaneously without repetitive inputs or screen navigation.


## Getting Started

### How to Run
1. Clone or download this repo:
   ```bash
   git clone https://github.com/Yijie-LiScott/CP3406_Utility_APP.git
   
2. Open in Android Studio  
3. Run on an emulator or physical device (API 26+ recommended)  

---

## Composables

### UtilityApp()
- Contains the screen layout using a Scaffold and a Material 3 NavigationBar
- Toggles content between the primary Utility screen and the Settings screen
- Collects and observes reactive state (CurrencyUiState) emitted from Currency ViewModel
### UtilityScreen()
- Changed the starter counter with a complete real-time currency conversion interface
- Features an OutlinedTextField with decimal input support to set the base amount
- Handles asynchronous state transitions including a loading spinner(CircularProgressIndicator) and network failure handling with a retry button
- Displays live, dynamically converted rates using a performant LazyColumn rendering custom CurrencyCard components


### SettingsScreen()
- Provides user preferences to configure the base currency (e.g, AUD, SGD, USD, CNY, EUR) 
- Modifies main screen behavior dynamically by notifying CurrencyViewModel to fetch updated base exchange rates and trigger immediate recalculations 

---

## Key Concepts Covered

| Week | Concept                        | Used In                                                                          |
|------|--------------------------------|----------------------------------------------------------------------------------|
| 1    | Kotlin + Android Studio         | MainActivity.kt                                                                  |
| 2    | Jetpack Compose Layouts         | UtilityApp(), UtilityScreen(), SettingsScreen(), CurrencyCard                    |
| 3    | Material Design 3               | MobileTechUtilityAppTheme, MaterialTheme.typography, CardDefaults, NavigationBar |
| 4    | ViewModel | CurrencyViewModel.kt (MVVM pattern, reactive StateFlow, UI state encapsulation   |
| 5    | Retrofit  | CurrencyApi.kt, CurrencyRepository.kt (Asynchronous HTTP, Gson deserialization   |

---

## Suggested Extensions
- [√]Replace counter with a real utility: Replaced the counter placeholder with an instant multi-currency converter providing live, at-a-glance financial calculations  
- [√]Add a ViewModel for state management: Implemented CurrencyViewModel using unidirectional data flow (UDF) and StateFlow to manage loading, error, input, and rate calculation states  
- Use SharedPreferences or DataStore to persist settings  
- [√]Add a simple API call using Retrofit: Integrated Retrofit 2 +Gson with Kotlin Coroutines to fetch live exchange rates from the Open ExchangeRate API 
- [√]Comprehensive Unit Testing Suite: Added JUnit 4 test suites (CurrencyRepositoryTest and CurrencyViewModelTest) covering network repository responses and calculation accuracy
---

## 📚 License
This template is provided for educational use in CP3406.  
Feel free to modify and extend it for your assessment.
