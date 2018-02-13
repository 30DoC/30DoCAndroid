package com.palzzak.blur.ui.quiz

import com.palzzak.blur.network.APIService
import com.palzzak.blur.di.PerActivity
import com.palzzak.blur.network.response.Quiz
import com.palzzak.blur.network.response.QuizSet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
    private lateinit var mQuizSet: QuizSet
    private val mAnswers = mutableMapOf<Int, Boolean>()


    override fun init(memberId: Long) {
        val call = mAPIService.randomQuiz()
        call.enqueue(object: Callback<QuizSet> {
            override fun onFailure(call: Call<QuizSet>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<QuizSet>?, response: Response<QuizSet>?) {
                mQuizSet = response?.body()!!
                mQuizView.printTextWithNumber(mQuizSet.quizList.size)
            }

        })
    }

    override fun setQuizAnswer(index: Int, answer: Boolean) {
        mAnswers[index] = answer
    }

    override fun loadQuiz() {
        mQuizView.setQuestions(mQuizSet)
    }

    override fun submitMyAnswers() {
        val result = 80
        mQuizView.showResultScreen(result)
    }
}