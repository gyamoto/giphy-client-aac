package com.gyamoto.giphy_client_aac.api.trend

import com.gyamoto.giphy_client_aac.api.ApiConst
import com.gyamoto.giphy_client_aac.api.model.Trend
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyTrendApi {

    @GET("v1/gifs/trending")
    suspend fun trending(
        @Query("api_key") apiKey: String = ApiConst.API_KEY,
        @Query("limit") limit: Int = LIMIT,
        @Query("offset") offset: Int = 0,
        @Query("rating") rating: String = RATING,
        @Query("fmt") format: String = FORMAT
    ): Trend

    companion object {

        private const val LIMIT = 25

        private const val RATING = "g"

        private const val FORMAT = "json"
    }
}

interface GiphyTrendApiBuilder {

    companion object {

        fun build(converterFactory: Converter.Factory, client: OkHttpClient): GiphyTrendApi {

            return Retrofit.Builder()
                .baseUrl(ApiConst.BASE_URL)
                .addConverterFactory(converterFactory)
                .client(client)
                .build()
                .create(GiphyTrendApi::class.java)
        }
    }
}
