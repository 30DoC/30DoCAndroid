package com.palzzak.blur.ui.splash

import com.palzzak.blur.network.APIService
import com.palzzak.blur.di.PerActivity
import com.palzzak.blur.network.data.MemberId
import com.palzzak.blur.network.data.ServiceStatus
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

    override fun logIn(mobileId: String, memberId: Long) {
        // Request log in to network module
        if (mobileId.isEmpty() || memberId == -1L) {
            val generatedMobileId = IdGenerator.createRandomId()
            mAPIService.logIn(generatedMobileId).enqueue(object : Callback<MemberId> {
                override fun onFailure(call: Call<MemberId>, t: Throwable) {
                    AppLogger.e(t.toString())
                }

                override fun onResponse(call: Call<MemberId>?, response: Response<MemberId>) {
                    val resMemberId = response.body()?.userId ?: -1L

                    if (resMemberId == -1L) {
                        logIn(IdGenerator.createRandomId(), resMemberId)
                    } else {
                        mSplashView.saveIdPreference(generatedMobileId, resMemberId)
                        mSplashView.showToast("Logged in by ID : $resMemberId")
                        mSplashView.setStatus(ServiceStatus.NONE)
                    }
                }
            })
        } else {
            mAPIService.observeStatus(memberId).enqueue(object: Callback<ServiceStatus> {
                override fun onFailure(call: Call<ServiceStatus>, t: Throwable) {
                    AppLogger.e("Failed")
                }

                override fun onResponse(call: Call<ServiceStatus>, response: Response<ServiceStatus>) {
                    val status = response.body()?.status ?: ServiceStatus.NONE
                    mSplashView.setStatus(status)
                }

            })
        }
    }
}