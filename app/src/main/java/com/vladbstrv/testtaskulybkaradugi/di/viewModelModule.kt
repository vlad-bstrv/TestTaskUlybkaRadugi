package com.vladbstrv.testtaskulybkaradugi.di

import com.vladbstrv.testtaskulybkaradugi.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}