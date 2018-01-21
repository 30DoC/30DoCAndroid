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
        mAppExecutor.diskIO().execute{
            val messages = mMessageDao.getMessages()
            mAppExecutor.mainThread().execute{
                callback.onMessagesLoaded(messages)
            }
        }
    }

    override fun getMessage(id: String, callback: MessagesDataSource.GetMessageCallback) {
        mAppExecutor.diskIO().execute{
            val message = mMessageDao.getMessageById(id)
            mAppExecutor.mainThread().execute{
                callback.onMessageLoaded(message)
            }
        }
    }

    override fun saveMessage(message: Message) {
        mAppExecutor.diskIO().execute{
            mMessageDao.insertMessage(message)
        }
    }

    override fun deleteAllMessages() {
        mAppExecutor.diskIO().execute {
            mMessageDao.deleteMessages()
            TODO("not implemented") // And also delete the audio files delivered
        }
    }

    override fun deleteMessage(id: String) {
        mAppExecutor.diskIO().execute {
            mMessageDao.deleteMessageById(id)
            TODO("not implemented") // And also delete the audio file delivered
        }
    }
}