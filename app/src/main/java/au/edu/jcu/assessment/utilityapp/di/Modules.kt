package au.edu.jcu.assessment.utilityapp.di

import au.edu.jcu.assessment.utilityapp.CurrencyViewModel
import au.edu.jcu.assessment.utilityapp.data.CurrencyApiService
import au.edu.jcu.assessment.utilityapp.data.CurrencyRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModules = module {
    single<CoroutineDispatcher> { Dispatchers.IO }

    single {
        val json = Json { ignoreUnknownKeys = true }
        Retrofit.Builder()
            .baseUrl("https://open.er-api.com/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    single { get<Retrofit>().create(CurrencyApiService::class.java) }

    single { CurrencyRepository(get(), get()) }

    viewModel { CurrencyViewModel(get()) }
}