package com.palzzak.blur.data.source

import com.palzzak.blur.data.Message
import com.palzzak.blur.network.data.MessageSet

/**
 * Created by yooas on 2018-01-19.
 */
interface MessagesDataSource {
    interface LoadMessagesCallback {
        fun onMessagesLoaded(messages: MessageSet)
    }

    interface GetMessageCallback {
        fun onMessageLoaded(message: MessageSet)
    }

    fun getMessages(roomId: Long, offset: Long, callback: LoadMessagesCallback)

    fun saveMessage(message: Message)

    fun deleteAllMessages()

    fun deleteMessage(id: Long)
}