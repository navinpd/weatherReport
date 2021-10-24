package com.big.myweather.di.builder

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.big.myapplication.commons.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [(ViewModelBuilder::class)])
internal abstract class ViewModelFactoryBuilder {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory

}