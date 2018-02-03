package com.palzzak.blur.ui.intro

import com.palzzak.blur.di.PerActivity
import javax.inject.Inject

/**
 * Created by jaeyoonyoo on 2018. 1. 12..
 */

@PerActivity
class IntroPresenter @Inject constructor(): IntroContract.Presenter {
    @Inject
    lateinit var mIntroView: IntroContract.View
}