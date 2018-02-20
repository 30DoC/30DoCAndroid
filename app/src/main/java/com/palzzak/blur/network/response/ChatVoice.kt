package com.palzzak.blur.network.response

/**
 * Created by jaeyoonyoo on 2018. 2. 20..
 */
data class ChatVoice(
        val fileName: String,
        val fileUrl: String,
        val regDate: String,
        val registId: Long,
        val roomId: Long,
        val voiceId: Long
)