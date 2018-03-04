package com.palzzak.blur.data.source.local

import com.palzzak.blur.data.Message
import com.palzzak.blur.data.source.MessagesDataSource
import com.palzzak.blur.network.data.MessageSet
import com.palzzak.blur.util.CoroutineContexts
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by yooas on 2018-01-19.
 */
@Singleton
class MessagesLocalDataSource @Inject constructor(val mCoroutineContexts: CoroutineContexts, val mMessageDao: MessageDao): MessagesDataSource {

    override fun getMessages(roomId: Long, offset: Long, callback: MessagesDataSource.LoadMessagesCallback) {
        launch(mCoroutineContexts.diskIO()) {
            val messages = mMessageDao.getMessages()
            callback.onMessagesLoaded(MessageSet(messages, -1L))
        }
    }

    override fun saveMessage(message: Message) {
        launch(mCoroutineContexts.diskIO()) {
            mMessageDao.insertMessage(message)
        }
    }

    override fun deleteAllMessages() {
        launch(mCoroutineContexts.diskIO()) {
            mMessageDao.deleteMessages()
            TODO("Delete audio files")
        }
    }

    override fun deleteMessage(id: Long) {
        launch(mCoroutineContexts.diskIO()) {
            mMessageDao.deleteMessageById(id)
            TODO("Delete audio file")
        }
    }
}