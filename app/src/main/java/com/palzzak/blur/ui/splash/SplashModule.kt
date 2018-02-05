package com.palzzak.blur.ui.splash

import com.palzzak.blur.di.PerActivity
import dagger.Binds
import dagger.Module

/**
 * Created by yooas on 2018-01-11.
 */
@Module
abstract class SplashModule {
    @Binds
    abstract fun view(splashActivity: SplashActivity): SplashContract.View

    @PerActivity
    @Binds
    abstract fun presenter(splashPresenter: SplashPresenter): SplashContract.Presenter
}