package com.palzzak.blur.data.source.remote

import com.palzzak.blur.data.Message
import com.palzzak.blur.data.source.MessagesDataSource
import com.palzzak.blur.network.APIService
import com.palzzak.blur.network.data.MessageSet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by jaeyoonyoo on 2018. 2. 20..
 */

@Singleton
class MessagesRemoteDataSource @Inject constructor(val mAPIService: APIService): MessagesDataSource {

    override fun getMessages(roomId: Long, offset: Long, callback: MessagesDataSource.LoadMessagesCallback) {
        mAPIService.observeRoom(roomId, offset).enqueue(object: Callback<MessageSet> {
            override fun onFailure(call: Call<MessageSet>?, t: Throwable?) {}

            override fun onResponse(call: Call<MessageSet>?, response: Response<MessageSet>?) {
                response?.body()?.run {
                    callback.onMessagesLoaded(this)
                }
            }

        })
    }

    override fun saveMessage(message: Message) {}

    override fun deleteAllMessages() {}

    override fun deleteMessage(id: Long) {}
}