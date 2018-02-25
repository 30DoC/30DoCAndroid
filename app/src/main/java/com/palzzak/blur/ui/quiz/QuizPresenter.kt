package com.palzzak.blur.ui.quiz

import com.palzzak.blur.network.APIService
import com.palzzak.blur.di.PerActivity
import com.palzzak.blur.network.data.QuizSet
import com.palzzak.blur.network.data.MemberId
import com.palzzak.blur.network.data.RoomId
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

    private var mResult: Int = -1

    private lateinit var mReceivedQuizSet: QuizSet

    private val mMyAnswers = mutableMapOf<Int, Boolean>()
    override fun init(memberId: Long) {
        val call = mAPIService.randomQuiz()
        call.enqueue(object: Callback<QuizSet> {
            override fun onFailure(call: Call<QuizSet>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<QuizSet>?, response: Response<QuizSet>?) {
                response?.body()?.run {
                    mReceivedQuizSet = this
                    mQuizView.printTextWithNumber(this.quizList.size)
                }
            }

        })
    }

    override fun setQuizAnswer(index: Int, answer: Boolean) {
        mMyAnswers[index] = answer
    }


    override fun loadQuiz() {
        mQuizView.setQuestions(mReceivedQuizSet)
    }

    override fun calculateResult(memberId: Long) {
        var correct = 0
        mReceivedQuizSet.quizList!!.map {
            if (it.answer == mMyAnswers[mReceivedQuizSet.quizList!!.indexOf(it)]) correct++
        }
        mResult = (correct.toDouble() / mMyAnswers.size * 100).toInt()

        mQuizView.showResultScreen(mResult)
    }

    override fun refreshViewIfPassed(memberId: Long) {
        if (mResult >= Constants.QUIZ_PASSING_SCORE) {
            mQuizView.congratulations(mResult)
            mAPIService.choice(memberId).enqueue(object: Callback<MemberId> {
                override fun onFailure(call: Call<MemberId>?, t: Throwable?) {}

                override fun onResponse(call: Call<MemberId>?, response: Response<MemberId>?) {}

            })
        }
    }

    override fun createRoom(memberId: Long) {
        mAPIService.createRoom(memberId, mReceivedQuizSet.memberId).enqueue(object: Callback<RoomId> {
            override fun onFailure(call: Call<RoomId>?, t: Throwable?) {}

            override fun onResponse(call: Call<RoomId>?, response: Response<RoomId>?) {
                val roomId = response?.body()?.roomId ?: -1L
                mQuizView.goToChatActivity(mReceivedQuizSet.memberId, roomId)
            }

        })
    }

    override fun choiceCancel(memberId: Long) {
        mAPIService.choiceCancel(memberId).enqueue(object: Callback<MemberId> {
            override fun onFailure(call: Call<MemberId>?, t: Throwable?) {}

            override fun onResponse(call: Call<MemberId>?, response: Response<MemberId>?) {}

        })
    }
}
