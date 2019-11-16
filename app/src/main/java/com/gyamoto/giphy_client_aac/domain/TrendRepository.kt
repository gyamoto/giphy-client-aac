package com.gyamoto.giphy_client_aac.domain

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.gyamoto.giphy_client_aac.api.model.Gif
import com.gyamoto.giphy_client_aac.api.model.Pagination
import com.gyamoto.giphy_client_aac.api.trend.GiphyTrendApi
import javax.inject.Inject

class TrendRepository @Inject constructor(
    private val trendApi: GiphyTrendApi
) {

    fun getTrendDataSource(): LiveData<PagedList<Gif>> {
        val dataSourceFactory = TrendDataSourceFactory(trendApi)
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(20)
            .setPageSize(20)
            .build()
        return LivePagedListBuilder<Pagination, Gif>(dataSourceFactory, pagedListConfig).build()
    }

}
