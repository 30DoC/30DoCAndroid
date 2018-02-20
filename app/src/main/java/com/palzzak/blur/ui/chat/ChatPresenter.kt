package com.palzzak.blur.ui.chat

import com.example.yooas.websocketchatter.Recorder
import com.palzzak.blur.data.source.MessagesRepository
import com.palzzak.blur.di.PerActivity
import com.palzzak.blur.network.response.ChatVoice
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

    @Inject
    lateinit var mMessagesRepository: MessagesRepository

    override fun observeRoom(roomId: Long, offset: Long) {
        val fetchedChats: List<ChatVoice>


        //mChatView.refreshChat()
    }
}