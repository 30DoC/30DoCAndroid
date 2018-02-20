package com.palzzak.blur.ui.chat

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.palzzak.blur.R
import com.palzzak.blur.util.AlertDialogFactory
import com.palzzak.blur.util.Constants
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_chat.*
import javax.inject.Inject

/**
 * Created by jaeyoonyoo on 2018. 2. 11..
 */
class ChatActivity: DaggerAppCompatActivity(), ChatContract.View, View.OnClickListener{

    @Inject
    lateinit var mChatPresenter: ChatPresenter

    @Inject
    lateinit var mSharedPref: SharedPreferences

    private lateinit var mAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        id_prev_button.setOnClickListener(this)

        val memberId = mSharedPref.getLong(Constants.PREF_MEMBER_ID_KEY, -1)
        mAdapter = ChatAdapter(memberId)
        id_chat_recycler.layoutManager = LinearLayoutManager(this)
        id_chat_recycler.adapter = mAdapter
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.id_prev_button -> {
                AlertDialogFactory.show(fragmentManager, Constants.DIALOG_CHAT_TAG_QUIT)
            }
        }
    }
}