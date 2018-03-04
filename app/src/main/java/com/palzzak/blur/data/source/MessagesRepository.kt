package com.palzzak.blur.data.source

import com.palzzak.blur.data.Message
import com.palzzak.blur.data.source.local.MessagesLocalDataSource
import com.palzzak.blur.data.source.remote.MessagesRemoteDataSource
import com.palzzak.blur.network.data.MessageSet
import javax.inject.Inject

/**
 * Created by yooas on 2018-01-19.
 */
class MessagesRepository: MessagesDataSource {
    @Inject
    lateinit var mMessagesLocalDataSource: MessagesLocalDataSource

    @Inject
    lateinit var mMessagesRemoteDataSource: MessagesRemoteDataSource

    private val mCachedMessages: MutableMap<Long, Message> = LinkedHashMap()
    private var mIsCacheDirty = false
    private var mOffset = -1L

    override fun getMessages(roomId: Long, offset: Long, callback: MessagesDataSource.LoadMessagesCallback) {
        if (!mIsCacheDirty && !mCachedMessages.isEmpty()) {
            callback.onMessagesLoaded(MessageSet(ArrayList(mCachedMessages.values), mOffset))
            return
        }

        if (mCachedMessages.isEmpty()) {
            mMessagesLocalDataSource.getMessages(roomId, offset, object: MessagesDataSource.LoadMessagesCallback {
                override fun onMessagesLoaded(messages: MessageSet) {
                    messages.chatVoiceList.map {
                        saveMessageIntoCache(it)
                    }
                }
            })
        }

        mMessagesRemoteDataSource.getMessages(roomId, offset, object: MessagesDataSource.LoadMessagesCallback {
            override fun onMessagesLoaded(messages: MessageSet) {
                mOffset = messages.offset
                messages.chatVoiceList.map {
                    saveMessage(it)
                }
            }
        })

        callback.onMessagesLoaded(MessageSet(ArrayList(mCachedMessages.values), mOffset))
    }

    private fun saveMessageIntoCache(message: Message) {
        mCachedMessages[message.voiceId] = message
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
            mCachedMessages[it.voiceId] = it
        }
    }

    override fun saveMessage(message: Message) {
        mMessagesLocalDataSource.saveMessage(message)
        mCachedMessages[message.voiceId] = message
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