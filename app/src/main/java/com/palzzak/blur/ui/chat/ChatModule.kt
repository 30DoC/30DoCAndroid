package com.palzzak.blur.ui.chat

import com.palzzak.blur.di.PerActivity
import com.palzzak.blur.util.AudioRecorder
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
    }
}