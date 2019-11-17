package com.gyamoto.giphy_client_aac.domain

import androidx.lifecycle.MutableLiveData
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

    private var retry: (() -> Any)? = null

    val initialLoad = MutableLiveData<NetworkState>()
    val networkState = MutableLiveData<NetworkState>()

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.invoke()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    override fun loadInitial(
        params: LoadInitialParams<Pagination>,
        callback: LoadInitialCallback<Pagination, Gif>
    ) {
        launch {

            networkState.postValue(NetworkState.LOADING)
            initialLoad.postValue(NetworkState.LOADING)

            runCatching {
                trendApi.trending()
            }.onSuccess {
                networkState.postValue(NetworkState.LOADED)
                initialLoad.postValue(NetworkState.LOADED)
                callback.onResult(it.data, null, it.pagination.next())
            }.onFailure {
                val error = NetworkState.error(it.message ?: it.localizedMessage)
                networkState.postValue(error)
                initialLoad.postValue(error)
                retry = { loadInitial(params, callback) }
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

            networkState.postValue(NetworkState.LOADING)

            runCatching {
                trendApi.trending(offset = params.key.offset)
            }.onSuccess {
                networkState.postValue(NetworkState.LOADED)
                callback.onResult(it.data, it.pagination.next())
            }.onFailure {
                networkState.postValue(NetworkState.error(it.message ?: it.localizedMessage))
                retry = { loadAfter(params, callback) }
            }
        }
    }
}
