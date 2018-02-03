package com.palzzak.blur.ui.quiz

import com.palzzak.blur.di.PerActivity
import dagger.Binds
import dagger.Module

/**
 * Created by yooas on 2018-01-07.
 */
@Module
abstract class QuizModule {
    @Binds
    abstract fun view(quizActivity: QuizActivity): QuizContract.View

    @PerActivity
    @Binds
    abstract fun presenter(quizPresenter: QuizPresenter): QuizContract.Presenter
}

