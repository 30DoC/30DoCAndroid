package com.palzzak.blur.ui.chat

import com.example.yooas.websocketchatter.Recorder
import com.palzzak.blur.di.PerActivity
import javax.inject.Inject

/**
 * Created by jaeyoonyoo on 2018. 2. 11..
 */

@PerActivity
class ChatPresenter @Inject constructor(): ChatContract.Presenter {
    @Inject
    lateinit var mChatView: ChatContract.View

    @Inject
    lateinit var mRecorder: Recorder
}