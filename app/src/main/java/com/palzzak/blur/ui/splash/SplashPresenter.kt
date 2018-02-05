package com.palzzak.blur.ui.splash

import com.palzzak.blur.ui.intro.IntroActivity
import com.palzzak.blur.network.NetworkService
import com.palzzak.blur.di.PerActivity
import com.palzzak.blur.util.IdGenerator
import com.palzzak.blur.network.ServiceStatus
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

    override fun logIn(mobileId: String) {
        // Request log in to network module
        val resId: String

        when (mobileId.isEmpty()) {
            true -> resId = requestRegisteringWithGeneratedId()
            false -> resId = mNetworkService.logIn(mobileId)
        }

        mSplashView.showToast("Logged in by ID : $resId")

        val status = mNetworkService.observeStatus(mobileId)

        when (status) {
            ServiceStatus.ID_INVALID -> mSplashView.somethingIsWrong()
            ServiceStatus.WAITING -> mSplashView.goToNextActivity(IntroActivity::class)
            // ServiceStatus.CHATTING -> mSplashView.goToNextActivity(ChatActivity::class)
        }
    }

    override fun requestRegisteringWithGeneratedId(): String {
        var response: String
        while (true) {
            var id = IdGenerator.createRandomId()
            response = mNetworkService.logIn(id)
            if (!response.isEmpty()) {
                mSplashView.saveIdPreference(id)
                break
            }
        }
        return response
    }
}