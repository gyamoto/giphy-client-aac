package com.gyamoto.giphy_client_aac.domain

import androidx.paging.PageKeyedDataSource
import com.gyamoto.giphy_client_aac.api.model.Gif
import com.gyamoto.giphy_client_aac.api.model.Pagination
import com.gyamoto.giphy_client_aac.api.trend.GiphyTrendApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class TrendDataSource(
    private val trendApi: GiphyTrendApi
) : PageKeyedDataSource<Pagination, Gif>(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override fun loadInitial(
        params: LoadInitialParams<Pagination>,
        callback: LoadInitialCallback<Pagination, Gif>
    ) {
        launch {
            runCatching {
                trendApi.trending()
            }.onSuccess {
                callback.onResult(it.data, null, it.pagination.next())
            }.onFailure {
                // FIXME: Error handling
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Pagination>,
        callback: LoadCallback<Pagination, Gif>
    ) {
        // nop
    }

    override fun loadAfter(
        params: LoadParams<Pagination>,
        callback: LoadCallback<Pagination, Gif>
    ) {
        launch {
            runCatching {
                trendApi.trending(offset = params.key.offset)
            }.onSuccess {
                callback.onResult(it.data, it.pagination.next())
            }.onFailure {
                // FIXME: Error handling
            }
        }
    }
}
