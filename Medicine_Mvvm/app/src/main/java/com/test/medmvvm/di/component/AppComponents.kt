package com.ogoul.kalamtime.di.component

import com.test.medmvvm.di.AppModule
import com.test.medmvvm.di.UtilsModule
import com.ogoul.kalamtime.base.BaseActivity
import com.test.medmvvm.base.MyApplication
import com.test.medmvvm.ui.activity.HomeActivity
import com.test.medmvvm.ui.activity.LoginActivity
import com.test.medmvvm.ui.activity.MedDetailsActivity
import com.ogoul.kalamtime.di.modules.ViewModelsModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UtilsModule::class, ViewModelsModule::class, AppModule::class])
interface AppComponents {
    fun doInjection(activity: MyApplication)
    fun doInjection(activity: BaseActivity)
    fun doInjection(activity: LoginActivity)
    fun doInjection(activity: HomeActivity)
    fun doInjection(activity: MedDetailsActivity)


}