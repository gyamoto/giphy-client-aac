package com.gyamoto.giphy_client_aac.domain

import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.gyamoto.giphy_client_aac.api.model.Gif
import com.gyamoto.giphy_client_aac.api.trend.GiphyTrendApi
import com.gyamoto.giphy_client_aac.uti.flatMap
import javax.inject.Inject

class TrendRepository @Inject constructor(
    private val trendApi: GiphyTrendApi
) {

    fun getTrendDataSource(): Listing<Gif> {

        val dataSourceFactory = TrendDataSourceFactory(trendApi)
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .build()

        val pagedList = dataSourceFactory.toLiveData(
            config = pagedListConfig
        )
        val source = dataSourceFactory.sourceLiveData

        return Listing(
            pagedList = pagedList,
            networkState = source.flatMap { it.networkState },
            refreshState = source.flatMap { it.initialLoad },
            refresh = { source.value?.invalidate() },
            retry = { source.value?.retryAllFailed() }
        )
    }
}
