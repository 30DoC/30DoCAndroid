package com.palzzak.blur.data.source.remote

import com.palzzak.blur.data.Message
import com.palzzak.blur.data.source.MessagesDataSource

/**
 * Created by jaeyoonyoo on 2018. 2. 20..
 */

class MessagesRemoteDataSource: MessagesDataSource {
    override fun getMessages(callback: MessagesDataSource.LoadMessagesCallback) {

    }

    override fun getMessage(id: Long, callback: MessagesDataSource.GetMessageCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveMessage(message: Message) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAllMessages() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteMessage(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}