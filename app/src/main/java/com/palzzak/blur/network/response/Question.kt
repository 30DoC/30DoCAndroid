package com.palzzak.blur.network.response

import java.util.*

/**
 * Created by stevehan on 2018. 2. 6..
 */
data class Question(
        val answer: Boolean,
        val question: String,
        val questionId: Int,
        val regDate: Date,
        val updateDate: Date
)