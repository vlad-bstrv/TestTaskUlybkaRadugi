package com.vladbstrv.testtaskulybkaradugi.di

import com.vladbstrv.testtaskulybkaradugi.ui.login.LoginViewModel
import com.vladbstrv.testtaskulybkaradugi.ui.order.OrderViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { OrderViewModel(get()) }
}