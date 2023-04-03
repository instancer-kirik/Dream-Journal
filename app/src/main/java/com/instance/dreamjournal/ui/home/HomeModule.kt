package com.instance.dreamjournal.ui.home

import androidx.lifecycle.viewmodel.compose.viewModel

import com.instance.dreamjournal.RecordingDetailsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf

import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::RecordingDetailsViewModel)

    single { MyPlayer(androidContext()) }
}