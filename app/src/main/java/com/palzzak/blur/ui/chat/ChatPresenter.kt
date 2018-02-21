package com.palzzak.blur.ui.chat

import android.os.Handler
import com.example.yooas.websocketchatter.AudioRecorder
import com.palzzak.blur.data.Message
import com.palzzak.blur.data.source.MessagesDataSource
import com.palzzak.blur.data.source.MessagesRepository
import com.palzzak.blur.di.PerActivity
import com.palzzak.blur.util.CoroutineContexts
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import javax.inject.Inject

/**
 * Created by jaeyoonyoo on 2018. 2. 11..
 */

@PerActivity
class ChatPresenter @Inject constructor(): ChatContract.Presenter {
    private val STATUS_WATING = 0
    private val STATUS_RECORDING = 1
    private val STATUS_STOPPED = 2
    private val STATUS_PLAYING = 3

    @Inject
    lateinit var mChatView: ChatContract.View

    @Inject
    lateinit var mAudioRecorder: AudioRecorder

    @Inject
    lateinit var mMessagesRepository: MessagesRepository

    @Inject
    lateinit var mCoroutineContexts: CoroutineContexts

    private var mRecordingStatus = STATUS_WATING

    lateinit var mRecordPath: String

    override fun observeRoom(roomId: Long, offset: Long) {
        mMessagesRepository.getMessages(roomId, offset, object: MessagesDataSource.LoadMessagesCallback {
            override fun onMessagesLoaded(messages: List<Message>?) {
                mChatView.showMessages(messages)
            }

        })
    }

    override fun controlRecording(handler: Handler) {
        when (mRecordingStatus) {
            STATUS_WATING -> startRecording(handler)
            STATUS_RECORDING -> stopRecording()
            STATUS_STOPPED -> playRecord()
            STATUS_PLAYING -> stopPlaying()
        }
    }


    override fun sendRecord() {
        mRecordingStatus = STATUS_WATING
        mChatView.showWaitingView()
    }

    private fun startRecording(handler: Handler) {
        mAudioRecorder.startRecording(mRecordPath, handler)
        mRecordingStatus = STATUS_RECORDING
        mChatView.showRecordingView()
    }

    private fun stopRecording() {
        mAudioRecorder.stopRecording()
        mRecordingStatus = STATUS_STOPPED
        mChatView.showStoppedView()
    }


    private fun playRecord() = runBlocking {
        mRecordingStatus = STATUS_PLAYING
        mChatView.showPlayingView()
        launch(mCoroutineContexts.diskIO()) {
            mAudioRecorder.playWavFile(mRecordPath)
        }.join()
        stopPlaying()
    }

    private fun stopPlaying() {
        mRecordingStatus = STATUS_STOPPED
        launch(mCoroutineContexts.diskIO()) {
            mAudioRecorder.stopPlaying()
        }
        mChatView.showStoppedView()
    }
}
