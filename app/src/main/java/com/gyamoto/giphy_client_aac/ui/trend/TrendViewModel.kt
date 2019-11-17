package com.gyamoto.giphy_client_aac.ui.trend

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.gyamoto.giphy_client_aac.api.model.Gif
import com.gyamoto.giphy_client_aac.domain.NetworkState
import com.gyamoto.giphy_client_aac.domain.TrendRepository
import com.gyamoto.giphy_client_aac.uti.map
import javax.inject.Inject

class TrendViewModel @Inject constructor(
    trendRepository: TrendRepository
) : ViewModel() {

    private val trend = trendRepository.getTrendDataSource()

    val loading: LiveData<Boolean> = trend.networkState.map { it == NetworkState.LOADING }

    val error: LiveData<String?> = trend.networkState.map { it.message }

    val images: LiveData<PagedList<Gif>> = trend.pagedList

    fun refresh() {
        trend.refresh()
    }

    fun retry() {
        trend.retry()
    }
}
