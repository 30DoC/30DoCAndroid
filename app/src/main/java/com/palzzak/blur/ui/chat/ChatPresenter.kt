package com.palzzak.blur.ui.chat

import com.palzzak.blur.data.source.MessagesDataSource
import com.palzzak.blur.data.source.MessagesRepository
import com.palzzak.blur.di.PerActivity
import com.palzzak.blur.network.APIService
import com.palzzak.blur.network.data.MessageSet
import com.palzzak.blur.util.AudioRecorder
import com.palzzak.blur.util.CoroutineContexts
import kotlinx.coroutines.experimental.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
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
    lateinit var mAPIService: APIService

    @Inject
    lateinit var mAudioRecorder: AudioRecorder

    @Inject
    lateinit var mMessagesRepository: MessagesRepository

    @Inject
    lateinit var mCoroutineContexts: CoroutineContexts

    private var mRecordingStatus = STATUS_WATING
    lateinit var mRecordPath: String
    private var mChatExecutor: ExecutorService? = null

    override fun controlRecording() {
        when (mRecordingStatus) {
            STATUS_WATING -> startRecording()
            STATUS_RECORDING -> stopRecording()
            STATUS_STOPPED -> playRecord()
            STATUS_PLAYING -> stopPlaying()
        }
        mChatView.updateRecordingButton(mRecordingStatus)
    }


    override fun sendRecord(roomId: Long, memberId: Long, mediaType: MediaType) {
        mRecordingStatus = STATUS_WATING
        mChatView.updateRecordingButton(mRecordingStatus)
        val file = File(mRecordPath)
        val fileBody = RequestBody.create(mediaType, file)
        val multipartBody = MultipartBody.Part.createFormData("files", file.name, fileBody)
        mAPIService.sendVoice(roomId, memberId, multipartBody).enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {}

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {}

        })
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


    private fun playRecord() {
        mRecordingStatus = STATUS_PLAYING
        launch(mCoroutineContexts.diskIO()) {
            mAudioRecorder.playWavFile(mRecordPath)
            mRecordingStatus = STATUS_STOPPED
            mChatView.updateRecordingButton(mRecordingStatus)
        }
    }

    private fun stopPlaying() {
        launch(mCoroutineContexts.diskIO()) {
            mAudioRecorder.stopPlaying()
        }
        mRecordingStatus = STATUS_STOPPED
    }

    override fun startObservingChat(roomId: Long) {
        if (mChatExecutor == null) mChatExecutor = Executors.newSingleThreadExecutor()
        mChatExecutor?.takeIf { mChatExecutor?.isShutdown == false }?.execute {
            while (true) {
                mMessagesRepository.getMessages(roomId, mChatView.getOffset(), object : MessagesDataSource.LoadMessagesCallback {
                    override fun onMessagesLoaded(messages: MessageSet) {
                        mChatView.updateChat(messages)
                    }

                })
                Thread.sleep(1000L)
            }
        }
    }

    override fun stopObservingChat() {
        mChatExecutor?.shutdown()
    }
}
