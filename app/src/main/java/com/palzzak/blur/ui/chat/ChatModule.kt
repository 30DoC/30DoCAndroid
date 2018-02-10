package com.palzzak.blur.ui.chat

import com.palzzak.blur.di.PerActivity
import dagger.Binds
import dagger.Module

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
}