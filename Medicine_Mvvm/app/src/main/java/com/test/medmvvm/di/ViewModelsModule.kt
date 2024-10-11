package com.ogoul.kalamtime.di.modules

import androidx.lifecycle.ViewModel
import com.test.medmvvm.viewmodel.factory.HomeViewModel
import com.ogoul.kalamtime.di.keys.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}