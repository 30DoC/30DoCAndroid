package com.palzzak.blur.ui.chat

import com.example.yooas.websocketchatter.AudioRecorder
import com.palzzak.blur.data.source.MessagesRepository
import com.palzzak.blur.data.source.local.MessagesLocalDataSource
import com.palzzak.blur.data.source.remote.MessagesRemoteDataSource
import com.palzzak.blur.di.PerActivity
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Created by jaeyoonyoo on 2018. 2. 11..
 */
@Module
abstract class ChatModule {
    @Binds
    abstract fun view(chatActivity: ChatActivity): ChatContract.View

    @PerActivity
    @Binds
    abstract fun presenter(chatPresenter: ChatPresenter): ChatContract.Presenter

    @Module
    companion object {
        @JvmStatic
        @PerActivity
        @Provides
        fun provideRecorder(): AudioRecorder = AudioRecorder()

        @JvmStatic
        @PerActivity
        @Provides
        fun provideMessagesRepository(): MessagesRepository = MessagesRepository()

        @JvmStatic
        @PerActivity
        @Provides
        fun provideMessagesLocalDataSource(): MessagesLocalDataSource = MessagesLocalDataSource()

        @JvmStatic
        @PerActivity
        @Provides
        fun provideMessagesRemoteDataSource(): MessagesRemoteDataSource = MessagesRemoteDataSource()
    }
}