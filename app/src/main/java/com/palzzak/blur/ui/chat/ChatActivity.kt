package com.palzzak.blur.ui.chat

import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

/**
 * Created by jaeyoonyoo on 2018. 2. 11..
 */
class ChatActivity: AppCompatActivity(), ChatContract.View{
    @Inject
    lateinit var mChatPresenter: ChatPresenter



}