package com.palzzak.blur.data.source

import android.app.Application
import android.arch.persistence.room.Room
import com.palzzak.blur.data.source.local.MessageDao
import com.palzzak.blur.data.source.local.MessagesDatabase
import com.palzzak.blur.data.source.local.MessagesLocalDataSource
import com.palzzak.blur.data.source.remote.MessagesRemoteDataSource
import com.palzzak.blur.network.APIService
import com.palzzak.blur.util.CoroutineContexts
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by jaeyoonyoo on 2018. 3. 4..
 */
@Module
abstract class MessagesRepositoryModule {
    @Module
    companion object {
        @JvmStatic
        @Singleton
        @Provides
        fun provideDb(context: Application): MessagesDatabase = Room.databaseBuilder(context.applicationContext, MessagesDatabase::class.java, "Messages.db").build()

        @JvmStatic
        @Singleton
        @Provides
        fun provideMessagesDao(db: MessagesDatabase): MessageDao = db.messageDao()

        @JvmStatic
        @Singleton
        @Provides
        fun provideMessagesLocalDataSource(coroutineContexts: CoroutineContexts, messageDao: MessageDao): MessagesLocalDataSource = MessagesLocalDataSource(coroutineContexts, messageDao)

        @JvmStatic
        @Singleton
        @Provides
        fun provideMessagesRemoteDataSource(apiService: APIService): MessagesRemoteDataSource = MessagesRemoteDataSource(apiService)

        @JvmStatic
        @Singleton
        @Provides
        fun provideMessagesRepository(localDataSource: MessagesLocalDataSource, remoteDataSource: MessagesRemoteDataSource): MessagesRepository = MessagesRepository(localDataSource, remoteDataSource)
    }
}