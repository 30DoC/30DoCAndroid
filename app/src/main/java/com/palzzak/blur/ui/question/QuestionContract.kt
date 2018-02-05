package com.palzzak.blur.ui.question

import com.palzzak.blur.BasePresenter
import com.palzzak.blur.BaseView

/**
 * Created by stevehan on 2018. 2. 1..
 */

interface QuestionContract {
    interface Presenter: BasePresenter<View> {

    }

    interface View: BaseView<Presenter> {

    }
}