package com.palzzak.blur.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.palzzak.blur.network.APIService
import com.palzzak.blur.util.Constants
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
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
        fun provideAPIService(): APIService = Retrofit.Builder()
                .baseUrl("http://13.124.185.204:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(APIService::class.java)
    }
}