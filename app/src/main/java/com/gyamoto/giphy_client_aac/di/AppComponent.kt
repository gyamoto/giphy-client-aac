package com.gyamoto.giphy_client_aac.di

import com.gyamoto.giphy_client_aac.GiphyApp
import com.gyamoto.giphy_client_aac.api.ApiModule
import com.gyamoto.giphy_client_aac.ui.UiModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelModule::class,
        ApiModule::class,
        UiModule::class
    ]
)
interface AppComponent : AndroidInjector<GiphyApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<GiphyApp>()
}
