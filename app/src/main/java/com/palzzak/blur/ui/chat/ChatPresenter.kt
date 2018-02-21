package com.palzzak.blur.ui.chat

import android.os.Handler
import com.example.yooas.websocketchatter.AudioRecorder
import com.palzzak.blur.data.Message
import com.palzzak.blur.data.source.MessagesDataSource
import com.palzzak.blur.data.source.MessagesRepository
import com.palzzak.blur.di.PerActivity
import com.palzzak.blur.util.CoroutineContexts
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

/**
 * Created by jaeyoonyoo on 2018. 2. 11..
 */

@PerActivity
class ChatPresenter @Inject constructor(): ChatContract.Presenter {
    val STATUS_WATING = 0
    val STATUS_RECORDING = 1
    val STATUS_STOPPED = 2
    val STATUS_PLAYING = 3

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
            STATUS_WATING -> startRecording()
            STATUS_RECORDING -> stopRecording()
            STATUS_STOPPED -> playRecord(handler)
            STATUS_PLAYING -> stopPlaying()
        }
        handler.sendEmptyMessage(mRecordingStatus)
    }


    override fun sendRecord(handler: Handler) {
        mRecordingStatus = STATUS_WATING
        handler.sendEmptyMessage(mRecordingStatus)
    }

    private fun startRecording() {
        mRecordingStatus = STATUS_RECORDING
        launch(mCoroutineContexts.diskIO()) {
            mAudioRecorder.startRecording(mRecordPath)
        }
    }

    private fun stopRecording() {
        mAudioRecorder.stopRecording()
        mRecordingStatus = STATUS_STOPPED
    }


    private fun playRecord(handler: Handler) {
        mRecordingStatus = STATUS_PLAYING
        launch(mCoroutineContexts.diskIO()) {
            mAudioRecorder.playWavFile(mRecordPath)
            mRecordingStatus = STATUS_STOPPED
            handler.sendEmptyMessage(mRecordingStatus)
        }
    }

    private fun stopPlaying() {
        launch(mCoroutineContexts.diskIO()) {
            mAudioRecorder.stopPlaying()
        }
        mRecordingStatus = STATUS_STOPPED
    }
}
