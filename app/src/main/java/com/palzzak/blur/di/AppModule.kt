package com.palzzak.blur.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.palzzak.blur.data.source.MessagesRepository
import com.palzzak.blur.data.source.local.MessagesLocalDataSource
import com.palzzak.blur.data.source.remote.MessagesRemoteDataSource
import com.palzzak.blur.network.APIService
import com.palzzak.blur.util.Constants
import com.palzzak.blur.util.CoroutineContexts
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Inject
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

        @JvmStatic
        @Singleton
        @Provides
        fun provideCoroutineContexts(): CoroutineContexts = CoroutineContexts(CoroutineContexts.getNewSingleThreadContext("diskIo"), CoroutineContexts.getNewFixedThreadPoolContext(CoroutineContexts.THREAD_COUNT, "networkIo"))
    }
}