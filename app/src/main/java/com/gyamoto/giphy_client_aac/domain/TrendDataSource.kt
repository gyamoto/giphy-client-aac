package com.gyamoto.giphy_client_aac.domain

import android.annotation.SuppressLint
import android.arch.paging.PageKeyedDataSource
import com.gyamoto.giphy_client_aac.api.model.Gif
import com.gyamoto.giphy_client_aac.api.model.Pagination
import com.gyamoto.giphy_client_aac.api.trend.GiphyTrendApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TrendDataSource(
    private val trendApi: GiphyTrendApi
) : PageKeyedDataSource<Pagination, Gif>() {

    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<Pagination>,
        callback: LoadInitialCallback<Pagination, Gif>
    ) {
        trendApi.trending()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onResult(it.data, null, it.pagination.next())
            }, {
                // FIXME: Error handling
            })
    }

    override fun loadBefore(
        params: LoadParams<Pagination>,
        callback: LoadCallback<Pagination, Gif>
    ) {
        // nop
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(
        params: LoadParams<Pagination>,
        callback: LoadCallback<Pagination, Gif>
    ) {
        trendApi.trending(offset = params.key.offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onResult(it.data, it.pagination.next())
            }, {
                // FIXME: Error handling
            })
    }

}
