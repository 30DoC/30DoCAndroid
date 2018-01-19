package com.paljjak.thirtydoc.data.source

import com.paljjak.thirtydoc.data.Message

/**
 * Created by yooas on 2018-01-19.
 */
interface MessagesDataSource {
    interface LoadMessagesCallback {
        fun onMessagesLoaded(messages: List<Message>)
        fun onDataNotAvailable()
    }

    interface GetMessageCallback {
        fun onMessageLoaded(message: Message)
        fun onDataNotAvailable()
    }

    fun getMessages(callback: LoadMessagesCallback)

    fun getMessage(id: String, callback: GetMessageCallback)

    fun saveMessage(message: Message)

    fun refreshTasks()

    fun deleteAllTasks()

    fun deleteTask(Id: String)
}