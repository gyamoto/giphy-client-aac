package com.gyamoto.giphy_client_aac.ui

import androidx.lifecycle.ViewModelProvider
import com.gyamoto.giphy_client_aac.di.GiphyViewModelFactory
import com.gyamoto.giphy_client_aac.ui.trend.TrendModule
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        TrendModule::class
    ]
)
interface UiModule {

    @Binds
    fun bindViewModelFactory(factory: GiphyViewModelFactory): ViewModelProvider.Factory
}
