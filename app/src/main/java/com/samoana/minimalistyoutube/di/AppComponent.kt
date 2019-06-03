package com.samoana.minimalistyoutube.di

import android.app.Application
import com.samoana.minimalistyoutube.MYTApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        FragmentsBindingModule::class
    ]
)
interface AppComponent: AndroidInjector<MYTApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application): Builder
        fun build(): AppComponent
    }
}