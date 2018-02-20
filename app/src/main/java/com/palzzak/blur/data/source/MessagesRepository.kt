package com.palzzak.blur.data.source

import com.palzzak.blur.data.Message
import javax.inject.Inject

/**
 * Created by yooas on 2018-01-19.
 */
class MessagesRepository: MessagesDataSource {
    @Inject
    lateinit var mMessagesLocalDataSource: MessagesDataSource

    @Inject
    lateinit var mMessagesRemoteDataSource: MessagesDataSource

    private val mCachedMessages: MutableMap<Long, Message> = LinkedHashMap()
    private var mIsCacheDirty = false

    override fun getMessages(roomId: Long, offset: Long, callback: MessagesDataSource.LoadMessagesCallback) {
        if (!mIsCacheDirty && !mCachedMessages.isEmpty()) {
            callback.onMessagesLoaded(ArrayList(mCachedMessages.values))
            return
        }

        if (mIsCacheDirty) {
            getMessagesFromRemoteDataSource(callback)
        } else {
            mMessagesLocalDataSource.getMessages(roomId, offset, object : MessagesDataSource.LoadMessagesCallback {
                override fun onMessagesLoaded(messages: List<Message>?) {
                    if (messages == null || messages.isEmpty()) {
                        return
                    }
                    refreshCache(messages)
                    refreshLocalDataSource(messages)
                    callback.onMessagesLoaded(ArrayList(mCachedMessages.values))
                }
            })
        }
    }

    private fun getMessagesFromRemoteDataSource(callback: MessagesDataSource.LoadMessagesCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun refreshLocalDataSource(messages: List<Message>) {
        mMessagesLocalDataSource.deleteAllMessages()
        messages.map {
            mMessagesLocalDataSource.saveMessage(it)
        }
    }

    private fun refreshCache(messages: List<Message>){
        mCachedMessages.clear()
        messages.map {
            mCachedMessages[it.mVoiceId] = it
        }
    }

    override fun saveMessage(message: Message) {
        mMessagesLocalDataSource.saveMessage(message)
        mCachedMessages[message.mVoiceId] = message
    }

    override fun deleteAllMessages() {
        mMessagesLocalDataSource.deleteAllMessages()
        mCachedMessages.clear()
    }

    override fun deleteMessage(id: Long) {
        mMessagesLocalDataSource.deleteMessage(id)
        mCachedMessages.remove(id)
    }
}