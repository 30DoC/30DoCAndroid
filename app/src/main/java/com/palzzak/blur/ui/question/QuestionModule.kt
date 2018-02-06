package com.palzzak.blur.ui.question

import com.palzzak.blur.di.PerActivity
import com.palzzak.blur.ui.intro.IntroContract
import com.palzzak.blur.ui.intro.IntroPresenter
import dagger.Binds
import dagger.Module

/**
 * Created by stevehan on 2018. 2. 1..
 */
@Module
abstract class QuestionModule {
    @Binds
    abstract fun view(questionActivity: QuestionActivity): QuestionContract.View

    @PerActivity
    @Binds
    abstract fun presenter(questionPresenter: QuestionPresenter): QuestionContract.Presenter
}