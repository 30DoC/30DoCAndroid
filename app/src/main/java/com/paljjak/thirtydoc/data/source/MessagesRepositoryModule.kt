package com.paljjak.thirtydoc.data.source

import android.app.Application
import android.arch.persistence.room.Room
import com.paljjak.thirtydoc.data.source.local.MessagesDatabase
import com.paljjak.thirtydoc.data.source.local.MessagesLocalDataSource
import com.paljjak.thirtydoc.util.AppExecutors
import dagger.Binds
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors
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
        internal fun provideAppExecutors(): AppExecutors {
            return AppExecutors (AppExecutors.DiskIOThreadExecutor(),
                    Executors.newFixedThreadPool(AppExecutors.THREAD_COUNT))
        }
    }
}