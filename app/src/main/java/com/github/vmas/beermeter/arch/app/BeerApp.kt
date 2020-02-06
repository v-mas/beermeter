package com.github.vmas.beermeter.arch.app

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.vmas.beermeter.arch.di.DependencyContainer
import com.github.vmas.beermeter.core.BeerRepository
import com.github.vmas.beermeter.core.BeerRepositoryImpl
import com.github.vmas.beermeter.core.BeerVolatileStore
import com.github.vmas.beermeter.screen.addbeer.AddBeerViewModel
import com.github.vmas.beermeter.screen.beerdetails.BeerDetailsViewModel
import com.github.vmas.beermeter.screen.beerlist.BeerListViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
class BeerApp : Application() {

    val dependencyContainer = object : DependencyContainer {

        // singleton
        override val gson: Gson by lazy {
            GsonBuilder().apply {
                //
            }.create()
        }

        // singleton
        override val beerRepository: BeerRepository by lazy { BeerRepositoryImpl(BeerVolatileStore()) }

        // factory - created new every time
        override val viewModelFactory: ViewModelProvider.Factory
            get() = object : ViewModelProvider.Factory {
                private val fallbackFactory = ViewModelProvider.NewInstanceFactory()

                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return when (modelClass) {
                        AddBeerViewModel::class.java -> AddBeerViewModel()
                        BeerListViewModel::class.java -> BeerListViewModel(beerRepository)
                        BeerDetailsViewModel::class.java -> BeerDetailsViewModel()
                        else -> fallbackFactory.create(modelClass)
                    } as T
                }
            }
    }
}
