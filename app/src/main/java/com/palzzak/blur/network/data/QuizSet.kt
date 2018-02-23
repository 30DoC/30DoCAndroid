package com.palzzak.blur.network.data

/**
 * Created by jaeyoonyoo on 2018. 2. 13..
 */
data class QuizSet(
        val userId: Long,
        val quizFormList: List<SimpleQuiz>? = null,
        val quizList: List<SimpleQuiz>? = null
)