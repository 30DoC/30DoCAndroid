package com.paljjak.thirtydoc.activities.splash

import com.paljjak.thirtydoc.BasePresenter
import com.paljjak.thirtydoc.BaseView
import com.paljjak.thirtydoc.data.network.ServiceStatus
import kotlin.reflect.KClass

/**
 * Created by yooas on 2018-01-11.
 */
interface SplashContract {
    interface View: BaseView<Presenter> {
        fun printText(text: String)
        fun<T: Any> goToNextActivity(activity: KClass<T>)
        fun somethingIsWrong()
    }

    interface Presenter: BasePresenter<View> {
        fun printInitialText()
        fun logIn(id: String)
        fun requestRegisteringWithGeneratedId(): ServiceStatus
    }
}