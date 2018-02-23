package com.palzzak.blur.ui.register

import com.palzzak.blur.di.PerActivity
import com.palzzak.blur.network.APIService
import com.palzzak.blur.network.data.QuizSet
import com.palzzak.blur.network.data.ServiceStatus
import com.palzzak.blur.network.data.SimpleQuiz
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by stevehan on 2018. 2. 1..
 */
@PerActivity
class RegisterPresenter @Inject constructor(): RegisterContract.Presenter {
    @Inject
    lateinit var mRegisterView: RegisterContract.View

    @Inject
    lateinit var mAPIService: APIService

    override fun loadInitialQuestionSet(memberId: Long) {
        mAPIService.inquireQuiz(memberId).enqueue(object: Callback<List<SimpleQuiz>> {
            override fun onFailure(call: Call<List<SimpleQuiz>>?, t: Throwable?) {}

            override fun onResponse(call: Call<List<SimpleQuiz>>?, response: Response<List<SimpleQuiz>>?) {
                val questions = response?.body()
                if (questions != null) mRegisterView.setQuestions(questions)
            }
        })
    }

    override fun registQuiz(memberId: Long, quizList: List<SimpleQuiz>) {
        val call = mAPIService.registQuiz(QuizSet(memberId, quizFormList = quizList))
        call.enqueue(object: Callback<ServiceStatus> {
            override fun onFailure(call: Call<ServiceStatus>?, t: Throwable?) {}

            override fun onResponse(call: Call<ServiceStatus>?, response: Response<ServiceStatus>) {
                if(response.body()?.status == ServiceStatus.WAITING) {
                    mRegisterView.finishActivity()
                }
            }

        })
    }
}
