package com.gyamoto.giphy_client_aac.ui.trend

import androidx.lifecycle.ViewModel
import com.gyamoto.giphy_client_aac.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class TrendModule {

    @ContributesAndroidInjector
    internal abstract fun contributeTrendActivity(): TrendActivity

    @Binds
    @IntoMap
    @ViewModelKey(TrendViewModel::class)
    abstract fun bindSpeakerViewModel(viewModel: TrendViewModel): ViewModel
}
