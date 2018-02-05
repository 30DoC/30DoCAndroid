package com.palzzak.blur.ui.intro

import com.palzzak.blur.BasePresenter
import com.palzzak.blur.BaseView

/**
 * Created by jaeyoonyoo on 2018. 1. 12..
 */

interface IntroContract {
    interface Presenter: BasePresenter<View> {

    }

    interface View: BaseView<Presenter> {

    }
}