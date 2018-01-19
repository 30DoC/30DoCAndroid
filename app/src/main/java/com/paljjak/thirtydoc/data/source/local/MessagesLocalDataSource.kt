package com.paljjak.thirtydoc.data.source.local

import com.paljjak.thirtydoc.data.Message
import com.paljjak.thirtydoc.data.source.MessagesDataSource
import com.paljjak.thirtydoc.util.AppExecutors
import javax.inject.Inject

/**
 * Created by yooas on 2018-01-19.
 */
class MessagesLocalDataSource: MessagesDataSource {
    @Inject
    lateinit var mAppExecutor: AppExecutors

    @Inject
    lateinit var mMessageDao: MessageDao

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