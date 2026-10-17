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
   
2. Open the project in Android Studio 
3. Sync Project with Gradle Files
4. Run on an emulator or physical device (API 26+ recommended)  

---

## Composables

### UtilityApp()
- Contains the screen layout using a Scaffold and a Material 3 NavigationBar
- Injects CurrencyViewModel using koinViewModel() and safely collects CurrencyUiState via collectAsStateWithLifecycle()
- Switches dynamically between the primary UtilityScreen and SettingScreen
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

| Week | Concept                            | Used In                                                                             |
|------|------------------------------------|-------------------------------------------------------------------------------------|
| 1    | Kotlin + Android Studio            | MainActivity.kt                                                                     |
| 2    | Jetpack Compose Layouts &UDF       | UtilityScreen.kt, SettingsScreen.kt, CurrencyCard                                   |
| 3    | Material Design 3 &Lifecycle State | MobileTechUtilityAppTheme, collectAsStateWithLifecycle(), NavigationBar             |
| 4    | MVVM &Dependency Injection(Koin)   | CurrencyViewModel.kt, Modules.kt, UtilityApplication.kt, KoinViewModel()            |
| 5    | Retrofit & Modern Networking       | CurrencyApiService.kt, CurrencyRepository.kt (kotlinx.serialization, NetworkResult) |

---

## Suggested Extensions
- [√]Replace counter with a real utility: Replaced the counter placeholder with an instant multi-currency converter providing live, at-a-glance financial calculations  
- [√]Add a ViewModel for state management: Implemented CurrencyViewModel using unidirectional data flow (UDF) and StateFlow to manage loading, error, input, and rate calculation states  
- [√]Koin Dependency Injection: Integrated Koin DI(koin-core, koin-android, koin-androidx-compose) to eliminate tightly coupled instances  
- [√]Modern Networking: Replaced legacy parsers with Retrofit2 + kotlinx.serialization and encapsulated responses using NetworkResult
- [√]Unit Testing Suite: Added JUnit 4 test suites (CurrencyRepositoryTest and CurrencyViewModelTest) covering network repository responses and calculation accuracy
---

## 📚 License
This template is provided for educational use in CP3406.  
Feel free to modify and extend it for your assessment.
