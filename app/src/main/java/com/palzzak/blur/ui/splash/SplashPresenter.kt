package com.palzzak.blur.ui.splash

import com.palzzak.blur.network.APIService
import com.palzzak.blur.di.PerActivity
import com.palzzak.blur.network.ServiceStatus
import com.palzzak.blur.util.IdGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by yooas on 2018-01-11.
 */

@PerActivity
class SplashPresenter @Inject constructor(): SplashContract.Presenter {
    @Inject
    lateinit var mSplashView: SplashContract.View

    @Inject
    lateinit var mAPIService: APIService

    override fun printInitialText() {
        mSplashView.printText("SPLASH")
    }

    override fun logIn(mobileId: String, memberId: String) {
        // Request log in to network module

        if (mobileId.isEmpty() || memberId == "-1") {
            val generatedMobileId = IdGenerator.createRandomId()
            mAPIService.signIn(generatedMobileId).enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    val resMemberId = response.body()

                    if (resMemberId == "-1") {
                        logIn(IdGenerator.createRandomId(), resMemberId)
                    } else {
                        mSplashView.saveIdPreference(generatedMobileId, resMemberId!!)
                        mSplashView.showToast("Logged in by ID : $resMemberId")
                        mSplashView.goToNextActivity(ServiceStatus.WAITING)
                    }

                }

            })
        } else {
            val memberIdLong = memberId.toLong()
            mAPIService.observeStatus(memberIdLong).enqueue(object: Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    val resStatus = response.body()

                    when (resStatus) {
                        "WAITING" -> mSplashView.goToNextActivity(ServiceStatus.WAITING)
                    }
                }

            })
        }
    }
}