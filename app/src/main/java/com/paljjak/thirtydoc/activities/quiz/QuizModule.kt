package com.paljjak.thirtydoc.activities.quiz

import com.paljjak.thirtydoc.di.PerActivity
import dagger.Binds
import dagger.Module
import dagger.Provides
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

