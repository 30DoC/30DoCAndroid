package com.palzzak.blur.activities.splash

import com.palzzak.blur.BasePresenter
import com.palzzak.blur.BaseView
import com.palzzak.blur.network.ServiceStatus
import kotlin.reflect.KClass

/**
 * Created by yooas on 2018-01-11.
 */
interface SplashContract {
    interface View: BaseView<Presenter> {
        fun printText(text: String)
        fun goToNextActivity(status: ServiceStatus)
        fun somethingIsWrong()
        fun saveIdPreference(id: String)
        fun showToast(s: String)
    }

    interface Presenter: BasePresenter<View> {
        fun printInitialText()
        fun logIn(id: String)
        fun requestRegisteringWithGeneratedId(): String
    }
}