package com.github.vmas.beermeter.arch.di

import androidx.lifecycle.ViewModelProvider
import com.github.vmas.beermeter.core.BeerRepository
import com.google.gson.Gson

interface DependencyContainer {
    val gson: Gson
    val beerRepository: BeerRepository
    val viewModelFactory: ViewModelProvider.Factory
}
