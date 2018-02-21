package com.palzzak.blur.ui.chat

import android.os.Handler
import com.palzzak.blur.BasePresenter
import com.palzzak.blur.BaseView
import com.palzzak.blur.data.Message

/**
 * Created by jaeyoonyoo on 2018. 2. 11..
 */
class ChatContract {
    interface Presenter: BasePresenter<View> {
        fun observeRoom(roomId: Long, offset: Long)
        fun controlRecording(handler: Handler)
        fun sendRecord(handler: Handler)
    }

    interface View: BaseView<Presenter> {
        fun showMessages(messages: List<Message>?)
        fun showRecordingView()
        fun showWaitingView()
        fun showStoppedView()
        fun showPlayingView()
    }
}