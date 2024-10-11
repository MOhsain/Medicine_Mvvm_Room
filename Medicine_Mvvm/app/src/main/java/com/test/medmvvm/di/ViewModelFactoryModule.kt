package com.ogoul.kalamtime.di.modules

import androidx.lifecycle.ViewModelProvider
import com.test.medmvvm.viewmodel.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
