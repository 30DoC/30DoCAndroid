package com.palzzak.blur.ui.quiz

import com.palzzak.blur.network.APIService
import com.palzzak.blur.di.PerActivity
import com.palzzak.blur.network.response.Quiz
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * Created by yooas on 2018-01-07.
 */
@PerActivity
class QuizPresenter @Inject constructor(): QuizContract.Presenter {
    @Inject
    lateinit var mQuizView: QuizContract.View

    @Inject
    lateinit var mAPIService: APIService
    private lateinit var mQuizzes: ArrayList<Quiz>
    private val mAnswers = mutableMapOf<Int, Boolean>()


    override fun init(memberId: Long) {
        //mQuizzes = mAPIService.getQuestions(memberId)
        mQuizzes = arrayListOf(
                Quiz(true, "너는 사람입니까?", 0, Date(), Date()),
                Quiz(true, "여자입니까?", 0, Date(), Date()),
                Quiz(true, "뭘봐요?", 0, Date(), Date()),
                Quiz(true, "키득키득?", 0, Date(), Date()),
                Quiz(true, "야 이거 최대로다가 텍스트 길이를 길게 써버리면 퀴즈 뷰에 어떻게 표시될까 넘나 궁금한 부분인 것을 함 테스트로서 재현해보자.", 0, Date(), Date())
        )
        mQuizView.printTextWithNumber(mQuizzes.size)
    }

    override fun setQuizAnswer(index: Int, answer: Boolean) {
        mAnswers[index] = answer
    }

    override fun loadQuiz() {
        mQuizView.setQuestions(mQuizzes)
    }

    override fun submitMyAnswers() {
        val result = 80
        mQuizView.showResultScreen(result)
    }
}