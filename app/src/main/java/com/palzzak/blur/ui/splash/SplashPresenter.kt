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

    override fun logIn(mobileId: String, memberId: Long) {
        // Request log in to network module

        if (mobileId.isEmpty() || memberId == -1L) {
            val generatedMobileId = IdGenerator.createRandomId()
            mAPIService.signIn(generatedMobileId).enqueue(object : Callback<Long> {
                override fun onFailure(call: Call<Long>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<Long>?, response: Response<Long>) {
                    val resMemberId = response.body()

                    if (resMemberId == -1L) {
                        logIn(IdGenerator.createRandomId(), resMemberId)
                    } else {
                        mSplashView.saveIdPreference(generatedMobileId, resMemberId!!)
                        mSplashView.showToast("Logged in by ID : $resMemberId")
                        mSplashView.goToNextActivity(ServiceStatus.WAITING)
                    }
                }
            })
        } else {
            mAPIService.observeStatus(memberId).enqueue(object: Callback<String> {
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