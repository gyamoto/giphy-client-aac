package com.gyamoto.giphy_client_aac.ui.trend

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.gyamoto.giphy_client_aac.api.model.Gif
import com.gyamoto.giphy_client_aac.api.model.Result
import com.gyamoto.giphy_client_aac.domain.TrendRepository
import javax.inject.Inject

class TrendViewModel @Inject constructor(
    trendRepository: TrendRepository
) : ViewModel() {

    private val _trend = trendRepository.getTrend()

    private val _loading = MediatorLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _images = MediatorLiveData<List<Gif>>()
    val images: LiveData<List<Gif>>
        get() = _images

    private val _error = MediatorLiveData<Throwable?>()
    val error: LiveData<Throwable?>
        get() = _error

    init {

        _loading.addSource(_trend) { result ->
            _loading.value = result is Result.Loading
        }

        _images.addSource(_trend) { result ->
            (result as? Result.Success)?.data?.let {
                _images.value = it
            }
        }

        _error.addSource(_trend) { result ->
            (result as? Result.Error)?.error?.let {
                _error.value = it
            }
        }

    }
}
