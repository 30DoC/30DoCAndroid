package com.palzzak.blur.network.response

/**
 * Created by jaeyoonyoo on 2018. 2. 13..
 */
data class QuizSet(
        val memberId: Long,
        val quizList: List<SimpleQuiz>
)