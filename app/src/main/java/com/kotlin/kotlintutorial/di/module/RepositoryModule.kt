package com.kotlin.kotlintutorial.di.module

import com.kotlin.kotlintutorial.data.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(get())
    }
}