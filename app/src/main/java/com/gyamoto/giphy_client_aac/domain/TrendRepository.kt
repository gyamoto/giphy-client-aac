package com.gyamoto.giphy_client_aac.domain

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.gyamoto.giphy_client_aac.api.model.Gif
import com.gyamoto.giphy_client_aac.api.model.Result
import com.gyamoto.giphy_client_aac.api.trend.GiphyTrendApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TrendRepository @Inject constructor(
    private val trendApi: GiphyTrendApi
) {

    @SuppressLint("CheckResult")
    fun getTrend(): LiveData<Result<List<Gif>>> {
        val result = MutableLiveData<Result<List<Gif>>>()

        result.value = Result.Loading

        trendApi.trending()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                result.value = Result.Success(it.data)
            }, {
                result.value = Result.Error(it)
            })

        return result
    }

}
