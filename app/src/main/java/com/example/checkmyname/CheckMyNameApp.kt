package com.example.checkmyname

import android.app.Application
import com.example.checkmyname.nameChecker.domain.NameUpdaterRepository
import com.example.checkmyname.nameChecker.presentation.NameCheckerViewModel
import com.example.checkmyname.probabilities.data.CountryProbabilitiesRepositoryImpl
import com.example.checkmyname.probabilities.data.IProbabilitiesService
import com.example.checkmyname.probabilities.data.PROBABILITIES_URL
import com.example.checkmyname.probabilities.domain.ProbabiltyRetrieverRepository
import com.example.checkmyname.probabilities.presentation.ProbabilitiesViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.binds
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class CheckMyNameApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CheckMyNameApp)
            updateModules()
        }
    }

    private fun KoinApplication.updateModules() {
        modules(module {
            single { CountryProbabilitiesRepositoryImpl(get()) } binds arrayOf(
                ProbabiltyRetrieverRepository::class,
                NameUpdaterRepository::class
            )

            single { provideRetrofit() }
            factory { NameCheckerViewModel(get()) }
            factory { ProbabilitiesViewModel(get()) }
        })
    }

    private fun provideRetrofit(): IProbabilitiesService {
        return Retrofit.Builder()
            .baseUrl(PROBABILITIES_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(IProbabilitiesService::class.java)

    }
}