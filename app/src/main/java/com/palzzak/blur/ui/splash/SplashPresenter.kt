package com.palzzak.blur.ui.splash

import com.palzzak.blur.network.APIService
import com.palzzak.blur.di.PerActivity
import com.palzzak.blur.network.response.ServiceStatus
import com.palzzak.blur.util.AppLogger
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
            val body = APIService.createSimpleRequestBody(generatedMobileId)
            mAPIService.signIn(body).enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onResponse(call: Call<String>?, response: Response<String>) {
                    val resMemberId = response.body()?.toLong() ?: -1L

                    if (resMemberId == -1L) {
                        logIn(IdGenerator.createRandomId(), resMemberId)
                    } else {
                        mSplashView.saveIdPreference(generatedMobileId, resMemberId)
                        mSplashView.showToast("Logged in by ID : $resMemberId")
                        mSplashView.goToNextActivity(ServiceStatus.WAITING)
                    }
                }
            })
        } else {
            val body = APIService.createSimpleRequestBody(memberId.toString())
            mAPIService.observeStatus(body).enqueue(object: Callback<ServiceStatus> {
                override fun onFailure(call: Call<ServiceStatus>, t: Throwable) {
                    AppLogger.e("Failed")
                }

                override fun onResponse(call: Call<ServiceStatus>, response: Response<ServiceStatus>) {
                    val resStatus = response.body()?.status ?: ServiceStatus.WAITING
                    mSplashView.goToNextActivity(ServiceStatus.WAITING)
                }

            })
        }
    }
}