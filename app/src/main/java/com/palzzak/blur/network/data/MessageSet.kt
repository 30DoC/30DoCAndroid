package com.palzzak.blur.network.data

import com.palzzak.blur.data.Message

/**
 * Created by jaeyoonyoo on 2018. 2. 25..
 */
data class MessageSet(val chatVoiceList: List<Message>,
                      val offset: Long)