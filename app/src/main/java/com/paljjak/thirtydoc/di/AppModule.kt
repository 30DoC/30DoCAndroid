package com.paljjak.thirtydoc.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.paljjak.thirtydoc.data.network.NetworkService
import com.paljjak.thirtydoc.util.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by yooas on 2018-01-07.
 */
@Module
abstract class AppModule {
    @Binds
    abstract fun bindContext(application: Application): Context

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideSharePreference(context: Context): SharedPreferences = context.getSharedPreferences(Constants.SHARD_PREF_COMMON_KEY, Context.MODE_PRIVATE)

        @JvmStatic
        @Singleton
        @Provides
        fun provideNetworkService() = NetworkService()
    }
}