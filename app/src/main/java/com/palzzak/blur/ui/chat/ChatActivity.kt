package com.palzzak.blur.ui.chat

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.webkit.MimeTypeMap
import com.palzzak.blur.R
import com.palzzak.blur.network.data.MessageSet
import com.palzzak.blur.util.AlertDialogFactory
import com.palzzak.blur.util.AppLogger
import com.palzzak.blur.util.Constants
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import okhttp3.MediaType
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
    private var mMemberId: Long = -1L
    private var mOpponentId: Long = -1L
    private var mRoomId: Long = -1L
    private var mOffset: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        mChatPresenter.mRecordPath = filesDir.absolutePath + "/recorded_audio.wav"

        id_prev_button.setOnClickListener(this)
        id_chat_record_button.setOnClickListener(this)
        id_chat_send_text.setOnClickListener(this)

        mMemberId = mSharedPref.getLong(Constants.PREF_MEMBER_ID_KEY, -1L)
        mOpponentId = mSharedPref.getLong(Constants.PREF_OPPONENT_ID_KEY, -1L)
        mRoomId = mSharedPref.getLong(Constants.PREF_ROOM_ID_KEY, -1L)

        mAdapter = ChatAdapter(mMemberId)

        id_chat_recycler.layoutManager = LinearLayoutManager(this)
        id_chat_recycler.adapter = mAdapter

        showWaitingView()
    }

    override fun onResume() {
        super.onResume()
        mOffset = mSharedPref.getLong(Constants.PREF_OFFSET_KEY, 0L)
        mChatPresenter.startObservingChat(mRoomId)
    }

    override fun onStop() {
        super.onStop()
        mChatPresenter.stopObservingChat()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.id_prev_button -> {
                AlertDialogFactory.show(fragmentManager, AlertDialogFactory.DIALOG_CHAT_TAG_QUIT)
            }
            R.id.id_chat_record_button -> {
                mChatPresenter.controlRecording()
            }
            R.id.id_chat_send_text -> {
                mChatPresenter.sendRecord(mRoomId, mMemberId, getMediaTypeFromPath(mChatPresenter.mRecordPath))
            }
        }
    }

    override fun getOffset(): Long = mOffset

    private fun showRecordingView() {
        AppLogger.d("start recording")
        id_chat_record_button.setImageResource(R.drawable.button_stop)
        id_chat_send_text.visibility = View.VISIBLE
    }

    private fun showWaitingView() {
        AppLogger.d("waiting for record")
        id_chat_record_button.setImageResource(R.drawable.button_record)
        id_chat_send_text.visibility = View.INVISIBLE
    }

    private fun showStoppedView() {
        AppLogger.d("recording or playing stopped")
        id_chat_record_button.setImageResource(R.drawable.button_play)
    }

    private fun showPlayingView() {
        AppLogger.d("playing record")
        id_chat_record_button.setImageResource(R.drawable.button_stop)
    }

    override fun updateRecordingButton(status: Int) {
        launch(UI) {
            when (status) {
                mChatPresenter.STATUS_STOPPED -> showStoppedView()
                mChatPresenter.STATUS_PLAYING -> showPlayingView()
                mChatPresenter.STATUS_WATING -> showWaitingView()
                mChatPresenter.STATUS_RECORDING -> showRecordingView()
            }
        }
    }

    override fun updateChat(messageSet: MessageSet) {
        (mAdapter.mData as ArrayList).addAll(messageSet.chatVoiceList)
        mAdapter.notifyDataSetChanged()

        mOffset = messageSet.offset
        mSharedPref.edit().putLong(Constants.PREF_OFFSET_KEY, mOffset).apply()
    }

    private fun getMediaTypeFromPath(path: String): MediaType {
        val extension = MimeTypeMap.getFileExtensionFromUrl(path)
        val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        return MediaType.parse(mimeType)!!
    }
}
