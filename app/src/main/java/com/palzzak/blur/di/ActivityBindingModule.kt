package com.palzzak.blur.di

import com.palzzak.blur.activities.intro.IntroActivity
import com.palzzak.blur.activities.intro.IntroModule
import com.palzzak.blur.activities.quiz.QuizActivity
import com.palzzak.blur.activities.quiz.QuizModule
import com.palzzak.blur.activities.splash.SplashActivity
import com.palzzak.blur.activities.splash.SplashModule
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

/**
 * Created by yooas on 2018-01-07.
 */
@Module(includes = [AndroidInjectionModule::class])
abstract class ActivityBindingModule {
    @PerActivity
    @ContributesAndroidInjector(modules = [QuizModule::class])
    abstract fun quizActivity(): QuizActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun splashActivity(): SplashActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [IntroModule::class])
    abstract fun introActivity(): IntroActivity
}