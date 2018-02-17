package com.palzzak.blur.ui.question

import com.palzzak.blur.di.PerActivity
import com.palzzak.blur.network.APIService
import com.palzzak.blur.network.response.SimpleQuiz
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject

/**
 * Created by stevehan on 2018. 2. 1..
 */
@PerActivity
class QuestionPresenter @Inject constructor(): QuestionContract.Presenter {
    @Inject
    lateinit var mQuestionView: QuestionContract.View

    @Inject
    lateinit var mAPIService: APIService


    override fun loadInitialQuestionSet(memberId: Long) {
        val call = mAPIService.inquireQuiz(memberId)
        call.enqueue(object: Callback<List<SimpleQuiz>> {
            override fun onFailure(call: Call<List<SimpleQuiz>>?, t: Throwable?) {}

            override fun onResponse(call: Call<List<SimpleQuiz>>?, response: Response<List<SimpleQuiz>>?) {
                val questions = response?.body()
                if (questions != null) mQuestionView.setQuestions(questions)
            }
        })
    }
}