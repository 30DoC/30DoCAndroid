package com.palzzak.blur.di

import com.palzzak.blur.ui.chat.ChatActivity
import com.palzzak.blur.ui.chat.ChatModule
import com.palzzak.blur.ui.intro.IntroActivity
import com.palzzak.blur.ui.intro.IntroModule
import com.palzzak.blur.ui.register.RegisterActivity
import com.palzzak.blur.ui.register.RegisterModule
import com.palzzak.blur.ui.quiz.QuizActivity
import com.palzzak.blur.ui.quiz.QuizModule
import com.palzzak.blur.ui.splash.SplashActivity
import com.palzzak.blur.ui.splash.SplashModule
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
    @ContributesAndroidInjector(modules = [RegisterModule::class])
    abstract fun registerActivity(): RegisterActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [SplashModule::class])
    abstract fun splashActivity(): SplashActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [IntroModule::class])
    abstract fun introActivity(): IntroActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [ChatModule::class])
    abstract fun chatActivity(): ChatActivity
}