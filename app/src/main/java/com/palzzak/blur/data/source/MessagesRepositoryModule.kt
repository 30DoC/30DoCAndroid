package com.palzzak.blur.data.source

import android.app.Application
import android.arch.persistence.room.Room
import com.palzzak.blur.data.source.local.MessagesDatabase
import com.palzzak.blur.data.source.local.MessagesLocalDataSource
import com.palzzak.blur.util.CoroutineContexts
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by yooas on 2018-01-19.
 */

@Module
abstract class MessagesRepositoryModule {
    @Singleton
    @Binds
    abstract fun bindMessagesLocalDataSource(dataSource: MessagesLocalDataSource): MessagesLocalDataSource

    @Module
    companion object {
        @JvmStatic
        @Singleton
        @Provides
        fun provideDb(context: Application) = Room.databaseBuilder(context.applicationContext, MessagesDatabase::class.java, "Messages.db").build()


        @JvmStatic
        @Singleton
        @Provides
        fun provideTasksDao(db: MessagesDatabase) = db.messageDao()


        @JvmStatic
        @Singleton
        @Provides
        fun provideCoroutineContexts(): CoroutineContexts = CoroutineContexts(
                CoroutineContexts.getNewSingleThreadContext("disk"),
                CoroutineContexts.getNewFixedThreadPoolContext(CoroutineContexts.THREAD_COUNT, "network"))
    }
}