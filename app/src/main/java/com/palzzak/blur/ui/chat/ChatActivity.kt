package com.palzzak.blur.ui.chat

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.palzzak.blur.R
import com.palzzak.blur.util.Constants
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_chat.*
import javax.inject.Inject

/**
 * Created by jaeyoonyoo on 2018. 2. 11..
 */
class ChatActivity: DaggerAppCompatActivity(), ChatContract.View{
    @Inject
    lateinit var mChatPresenter: ChatPresenter

    @Inject
    lateinit var mSharedPref: SharedPreferences

    private lateinit var mAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val memberId = mSharedPref.getLong(Constants.PREF_MEMBER_ID_KEY, -1)
        mAdapter = ChatAdapter(memberId)
        id_chat_recycler.layoutManager = LinearLayoutManager(this)
        id_chat_recycler.adapter = mAdapter
        //mAdapter.mData = mChatPresenter.observeRoom(roomId, offset)
        mAdapter.notifyDataSetChanged()
    }
}