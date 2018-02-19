package com.palzzak.blur.ui.register

import com.palzzak.blur.di.PerActivity
import dagger.Binds
import dagger.Module

/**
 * Created by stevehan on 2018. 2. 1..
 */
@Module
abstract class RegisterModule {
    @Binds
    abstract fun view(registerActivity: RegisterActivity): RegisterContract.View

    @PerActivity
    @Binds
    abstract fun presenter(registerPresenter: RegisterPresenter): RegisterContract.Presenter
}