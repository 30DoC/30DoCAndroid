package com.paljjak.thirtydoc.di

import android.app.Application
import com.paljjak.thirtydoc.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by yooas on 2018-01-07.
 */
@Singleton
@Component(modules = [
AppModule::class,
ActivityBindingModule::class,
AndroidSupportInjectionModule::class
])
interface AppComponent: AndroidInjector<MainApplication> {
    override fun inject(instance: MainApplication?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): AppComponent.Builder
        fun build(): AppComponent
    }
}