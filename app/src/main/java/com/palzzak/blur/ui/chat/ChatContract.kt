package com.palzzak.blur.ui.chat

import com.palzzak.blur.BasePresenter
import com.palzzak.blur.BaseView
import com.palzzak.blur.data.Message
import com.palzzak.blur.network.data.MessageSet
import okhttp3.MediaType

/**
 * Created by jaeyoonyoo on 2018. 2. 11..
 */
class ChatContract {
    interface Presenter: BasePresenter<View> {
        fun controlRecording()
        fun sendRecord(roomId: Long, memberId: Long, mediaType: MediaType)
        fun startObservingChat(roomId: Long)
        fun stopObservingChat()
    }

    interface View: BaseView<Presenter> {
        fun updateRecordingButton(status: Int)
        fun updateChat(messageSet: MessageSet)
        fun getOffset(): Long
    }
}