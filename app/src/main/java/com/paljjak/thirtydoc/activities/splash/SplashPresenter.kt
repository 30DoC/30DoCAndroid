package com.paljjak.thirtydoc.activities.splash

import com.paljjak.thirtydoc.activities.quiz.QuizActivity
import com.paljjak.thirtydoc.data.network.NetworkService
import com.paljjak.thirtydoc.di.PerActivity
import com.paljjak.thirtydoc.util.IdGenerator
import com.paljjak.thirtydoc.data.network.ServiceStatus
import javax.inject.Inject

/**
 * Created by yooas on 2018-01-11.
 */

@PerActivity
class SplashPresenter @Inject constructor(): SplashContract.Presenter {
    @Inject
    lateinit var mSplashView: SplashContract.View

    @Inject
    lateinit var mNetworkService: NetworkService

    override fun printInitialText() {
        mSplashView.printText("SPLASH")
    }

    override fun logIn(id: String) {
        // Request log in to network module
        var status: ServiceStatus
        if (id.isEmpty()) {
            status = requestRegisteringWithGeneratedId()
        } else {
            status = mNetworkService.logIn(id)
        }

        mSplashView.showToast("Logged in by ID : $id")

        when (status) {
            ServiceStatus.ID_INVALID -> mSplashView.somethingIsWrong()
            ServiceStatus.WAITING -> mSplashView.goToNextActivity(QuizActivity::class)
            // ServiceStatus.CHATTING -> mSplashView.goToNextActivity(ChatActivity::class.java)
        }
    }

    override fun requestRegisteringWithGeneratedId(): ServiceStatus {
        var response: ServiceStatus
        while (true) {
            var id = IdGenerator.createRandomId()
            response = mNetworkService.logIn(id)
            if (response != ServiceStatus.ID_INVALID) {
                mSplashView.saveIdPreference(id)
                break
            }
        }
        return response
    }
}