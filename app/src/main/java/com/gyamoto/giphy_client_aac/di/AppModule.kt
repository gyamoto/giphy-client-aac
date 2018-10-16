package com.gyamoto.giphy_client_aac.di

import android.content.Context
import com.gyamoto.giphy_client_aac.GiphyApp
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(application: GiphyApp): Context {
        return application.applicationContext
    }

}
