package com.example.finedust.presentation.di

import com.example.finedust.BuildConfig
import com.example.finedust.data.api.AirKoreaApiService
import com.example.finedust.data.api.KakaoLocalApiService
import com.example.finedust.presentation.util.Url
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.definition.BeanDefinition
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val kakaoModule : Module = module {

    single<KakaoLocalApiService> { get<Retrofit>().create(KakaoLocalApiService::class.java)}

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Url.AIR_KOREA_API_BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(get<GsonConverterFactory>())
            .build()
    }

    single<GsonConverterFactory> { GsonConverterFactory.create() }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .run {
                addInterceptor(get<HttpLoggingInterceptor>())
                build()
            }
    }

    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

}

val airKoreaModule : Module = module {

    single<AirKoreaApiService> { get<Retrofit>().create(AirKoreaApiService::class.java) }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Url.KAKAO_API_BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(get<GsonConverterFactory>())
            .build()
    }



    single<GsonConverterFactory> { GsonConverterFactory.create() }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .run {
                addInterceptor(get<HttpLoggingInterceptor>())
                build()
            }
    }

    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
}