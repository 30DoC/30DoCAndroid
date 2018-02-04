package com.palzzak.blur.network.response

import java.util.*

/**
 * Created by yooas on 2018-02-04.
 */
data class Quiz(
        val answer: Boolean,
        val question: String,
        val quizId: Long,
        val regDate: Date,
        val updateDate: Date
)