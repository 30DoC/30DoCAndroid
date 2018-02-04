package com.palzzak.blur.ui.splash

import com.palzzak.blur.BasePresenter
import com.palzzak.blur.BaseView
import com.palzzak.blur.network.ServiceStatus

/**
 * Created by yooas on 2018-01-11.
 */
interface SplashContract {
    interface View: BaseView<Presenter> {
        fun printText(text: String)
        fun goToNextActivity(status: ServiceStatus)
        fun somethingIsWrong()
        fun saveIdPreference(mobileId: String, memberId: String)
        fun showToast(s: String)
    }

    interface Presenter: BasePresenter<View> {
        fun printInitialText()
        fun logIn(mobileId: String, memberId: String)
    }
}