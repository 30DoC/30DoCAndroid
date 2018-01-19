package com.paljjak.thirtydoc.data.source

import com.paljjak.thirtydoc.data.Message
import javax.inject.Inject

/**
 * Created by yooas on 2018-01-19.
 */
class MessagesRepository: MessagesDataSource {
    @Inject
    lateinit var mMessagesLocalDataSource: MessagesDataSource

    override fun getMessages(callback: MessagesDataSource.LoadMessagesCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMessage(id: String, callback: MessagesDataSource.GetMessageCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveMessage(message: Message) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun refreshTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAllTasks() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteTask(Id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}