package com.palzzak.blur.ui.quiz

import com.palzzak.blur.network.APIService
import com.palzzak.blur.di.PerActivity
import com.palzzak.blur.network.data.QuizSet
import com.palzzak.blur.network.data.SimpleLong
import com.palzzak.blur.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by yooas on 2018-01-07.
 */
@PerActivity
class QuizPresenter @Inject constructor(): QuizContract.Presenter {
    @Inject
    lateinit var mQuizView: QuizContract.View

    @Inject
    lateinit var mAPIService: APIService
    private lateinit var mReceivedQuizSet: QuizSet
    private val mMyAnswers = mutableMapOf<Int, Boolean>()


    override fun init(memberId: Long) {
        val call = mAPIService.randomQuiz()
        call.enqueue(object: Callback<QuizSet> {
            override fun onFailure(call: Call<QuizSet>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<QuizSet>?, response: Response<QuizSet>?) {
                mReceivedQuizSet = response?.body()!!
                mQuizView.printTextWithNumber(mReceivedQuizSet.quizList.size)
            }

        })
    }

    override fun setQuizAnswer(index: Int, answer: Boolean) {
        mMyAnswers[index] = answer
    }

    override fun loadQuiz() {
        mQuizView.setQuestions(mReceivedQuizSet)
    }

    override fun submitMyAnswers(memberId: Long) {
        mAPIService.choice(memberId).enqueue(object: Callback<SimpleLong> {
            override fun onFailure(call: Call<SimpleLong>?, t: Throwable?) {}

            override fun onResponse(call: Call<SimpleLong>?, response: Response<SimpleLong>?) {}

        })


        var correct = 0
        mReceivedQuizSet.quizList.map {
            if (it.answer == mMyAnswers[mReceivedQuizSet.quizList.indexOf(it)]) correct++
        }
        val result = (correct.toDouble() / mMyAnswers.size * 100).toInt()

        mQuizView.showResultScreen(result)

        if (result >= Constants.QUIZ_PASSING_SCORE) {
            mQuizView.congratulations()
            mAPIService.createRoom(memberId, mReceivedQuizSet.memberId).enqueue(object: Callback<SimpleLong> {
                override fun onFailure(call: Call<SimpleLong>?, t: Throwable?) {}

                override fun onResponse(call: Call<SimpleLong>?, response: Response<SimpleLong>) {
                    response.body()?.apply {
                        mQuizView.goToChatActivity(mReceivedQuizSet.memberId, this.value)
                    }
                }

            })
        }
    }
}