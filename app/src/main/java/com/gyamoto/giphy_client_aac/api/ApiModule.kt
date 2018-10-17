package com.gyamoto.giphy_client_aac.api

import com.gyamoto.giphy_client_aac.api.trend.GiphyTrendApi
import com.gyamoto.giphy_client_aac.api.trend.GiphyTrendApiBuilder
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class ApiModule {

    @Provides
    fun provideGiphyTrendApi(
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): GiphyTrendApi {
        return GiphyTrendApiBuilder.build(converterFactory, okHttpClient)
    }

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideConverterFactory(moshi: Moshi): Converter.Factory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }

}

