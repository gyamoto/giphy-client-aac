package com.gyamoto.giphy_client_aac.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.gyamoto.giphy_client_aac.api.model.Gif
import com.gyamoto.giphy_client_aac.api.model.Pagination
import com.gyamoto.giphy_client_aac.api.trend.GiphyTrendApi
import javax.inject.Inject

class TrendDataSourceFactory @Inject constructor(
    private val trendApi: GiphyTrendApi
) : DataSource.Factory<Pagination, Gif>() {

    val sourceLiveData = MutableLiveData<TrendDataSource>()

    override fun create(): DataSource<Pagination, Gif> {
        val source = TrendDataSource(trendApi)
        sourceLiveData.postValue(source)
        return source
    }

}
