package com.kotlin.kotlintutorial.di.module

import com.kotlin.kotlintutorial.ui.main.viewModel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get(),get())
    }
}