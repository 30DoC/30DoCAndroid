package com.palzzak.blur.util

import android.media.*
import android.os.Handler
import java.io.DataInputStream
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * Created by yooas on 2018-01-17.
 */
class AudioRecorder {
    private val mSampleRates = arrayOf(44100, 22050, 11025, 8000)
    private val mAudioFormats = arrayOf(AudioFormat.ENCODING_PCM_16BIT, AudioFormat.ENCODING_PCM_8BIT, AudioFormat.ENCODING_DEFAULT)
    private val mChannelConfigs = arrayOf(AudioFormat.CHANNEL_IN_STEREO, AudioFormat.CHANNEL_IN_MONO, AudioFormat.CHANNEL_IN_DEFAULT)
    private val mBufferSize = 1024
    private val mBytesPerElement = 2
    private var mRecorder: AudioRecord? = null
    private var mSampleRate: Int = -1
    private var mAudioFormat: Int = -1
    private var mChannelConfig: Int = -1
    private var mIsRecording = false

    fun playWavFile(path: String) {
        val minBufferSize = AudioTrack.getMinBufferSize(mSampleRate, mChannelConfig, mAudioFormat)
        val audioTrack = AudioTrack(AudioManager.STREAM_VOICE_CALL, mSampleRate, mChannelConfig, mAudioFormat, minBufferSize, AudioTrack.MODE_STREAM)
        val data = ByteArray(minBufferSize)
        var count = 0

        val fis = FileInputStream(path)
        val dis = DataInputStream(fis)
        audioTrack.play()
        while (true) {
            count = dis.read(data, 0, minBufferSize)
            if (count <= -1) break
            audioTrack.write(data, 0, count)
        }
        audioTrack.stop()
        audioTrack.release()
        dis.close()
        fis.close()
    }

    fun stopRecording() {
        if (mRecorder == null) return
        mRecorder!!.stop()
        mRecorder!!.release()
        mIsRecording = false
    }

    fun startRecording(recordedAudioPath: String) {
        mRecorder = findAudioRecord()
        mRecorder!!.startRecording()
        val data = ShortArray(mBufferSize)
        mIsRecording = true
        writeAudioDataToFile(data, recordedAudioPath)
//        Thread({
//            sendVolumeToHandler(data, handler)
//        }).start()
    }

    private fun writeAudioDataToFile(data: ShortArray, recordedAudioPath: String) {
        var fos = FileOutputStream(recordedAudioPath)
        while (mIsRecording) {
            mRecorder!!.read(data, 0, mBufferSize)
            val bData = short2byte(data)
            fos.write(bData, 0, mBufferSize * mBytesPerElement)
        }
        fos.close()
    }

    private fun sendVolumeToHandler(data: ShortArray, handler: Handler) {
        while (mIsRecording) {
            if (data[0] > 0) {
                handler.sendEmptyMessage(data[0].toInt())
            }
        }
    }

    private fun findAudioRecord(): AudioRecord? {
        for (rate in mSampleRates) {
            for (format in mAudioFormats) {
                for (channel in mChannelConfigs) {
                    val bufferSize = AudioRecord.getMinBufferSize(rate, channel, format)
                    if (bufferSize == AudioRecord.ERROR_BAD_VALUE) continue
                    mSampleRate = rate
                    mAudioFormat = format
                    mChannelConfig = channel

                    val recorder = AudioRecord(MediaRecorder.AudioSource.DEFAULT, mSampleRate, mChannelConfig, mAudioFormat, bufferSize)
                    if (recorder.state == AudioRecord.STATE_INITIALIZED) {
                        return recorder
                    }
                }
            }
        }
        return null
    }

    private fun short2byte(sData: ShortArray): ByteArray {
        val shortArrsize = sData.size
        val bytes = ByteArray(shortArrsize * 2)
        for (i in 0 until shortArrsize) {
            bytes[i * 2] = (sData[i].toInt() and 0x00FF).toByte()
            bytes[i * 2 + 1] = (sData[i].toInt() shr 8).toByte()
            sData[i] = 0
        }
        return bytes
    }

    fun stopPlaying() {
        //TODO
    }
}