package com.test.medmvvm.base


import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.test.medmvvm.di.AppModule
import com.test.medmvvm.utils.SharedPrefsHelper
import com.ogoul.kalamtime.di.component.AppComponents
import com.ogoul.kalamtime.di.component.DaggerAppComponents
import javax.inject.Inject


class MyApplication : Application() {
    lateinit var component: AppComponents

    @Inject
    lateinit var sharedPrefsHelper: SharedPrefsHelper

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponents.builder().appModule(AppModule(this)).build()
        getAppComponent(this).doInjection(this)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    companion object {
        fun getAppComponent(context: Context): AppComponents {
            return (context.applicationContext as MyApplication).component
        }
    }


}