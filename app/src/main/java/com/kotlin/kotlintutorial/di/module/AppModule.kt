package com.kotlin.kotlintutorial.di.module

import android.content.Context
import com.kotlin.kotlintutorial.data.api.ApiHelper
import com.kotlin.kotlintutorial.data.api.ApiHelperImpl
import com.kotlin.kotlintutorial.data.api.ApiService
import com.kotlin.kotlintutorial.utils.AppConstants
import com.kotlin.kotlintutorial.utils.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), AppConstants.BASE_URL) }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }

    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }
}


private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loginInterceptor = HttpLoggingInterceptor()
    loginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loginInterceptor)
        .build()
} else {
    OkHttpClient.Builder().build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)


private fun provideApiHelper(apiHelperImpl: ApiHelperImpl): ApiHelper = apiHelperImpl