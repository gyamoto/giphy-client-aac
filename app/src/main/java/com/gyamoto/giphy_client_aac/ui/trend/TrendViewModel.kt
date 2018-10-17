package com.gyamoto.giphy_client_aac.ui.trend

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.gyamoto.giphy_client_aac.api.model.Gif
import com.gyamoto.giphy_client_aac.domain.TrendRepository
import javax.inject.Inject

class TrendViewModel @Inject constructor(
    trendRepository: TrendRepository
) : ViewModel() {

    private val _loading = MediatorLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MediatorLiveData<Throwable?>()
    val error: LiveData<Throwable?>
        get() = _error

    val images: LiveData<PagedList<Gif>>

    init {

        // FIXME: `PagedList` でLoading/Errorを表示する

        images = trendRepository.getTrendDataSource()
    }
}
