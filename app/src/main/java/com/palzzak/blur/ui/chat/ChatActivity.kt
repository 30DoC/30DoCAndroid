package com.palzzak.blur.ui.chat

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.palzzak.blur.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by jaeyoonyoo on 2018. 2. 11..
 */
class ChatActivity: DaggerAppCompatActivity(), ChatContract.View{
    @Inject
    lateinit var mChatPresenter: ChatPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
    }
}