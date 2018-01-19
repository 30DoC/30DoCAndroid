package com.paljjak.thirtydoc.activities.splash

import com.paljjak.thirtydoc.BasePresenter
import com.paljjak.thirtydoc.BaseView
import com.paljjak.thirtydoc.data.network.ServiceStatus

/**
 * Created by yooas on 2018-01-11.
 */
interface SplashContract {
    interface View: BaseView<Presenter> {
        fun printText(text: String)
        fun goToQuizActivity()
        fun goToChatActivity()
        fun somethingIsWrong()
    }

    interface Presenter: BasePresenter<View> {
        fun printInitialText()
        fun logIn(id: String)
        fun requestRegisteringWithGeneratedId(): ServiceStatus
    }
}