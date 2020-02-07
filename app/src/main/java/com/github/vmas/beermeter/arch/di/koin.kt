package com.github.vmas.beermeter.arch.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.vmas.beermeter.core.*
import com.github.vmas.beermeter.screen.addbeer.AddBeerFragment
import com.github.vmas.beermeter.screen.addbeer.AddBeerViewModel
import com.github.vmas.beermeter.screen.beerdetails.BeerDetailsFragment
import com.github.vmas.beermeter.screen.beerdetails.BeerDetailsViewModel
import com.github.vmas.beermeter.screen.beerlist.BeerListFragment
import com.github.vmas.beermeter.screen.beerlist.BeerListViewModel
import com.google.gson.GsonBuilder
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/*
 * Created by Sławomir Golonka @ ConciseSoftware on 07-02-2020.
 */

val appModule: Module
    get() = module {
        single {
            GsonBuilder().apply {
                //
            }.create()
        }
        single<BeerStore> { BeerVolatileStore() }
//        single<BeerStore> {
//            BeerSharedPrefsStore(
//                get<Context>().getSharedPreferences("beers", Context.MODE_PRIVATE),
//                get()
//            )
//        }
        single<BeerRepository> { BeerRepositoryImpl(get()) }

        viewModel { BeerListViewModel(get()) }
        viewModel { BeerDetailsViewModel(it[0]) }
        viewModel { AddBeerViewModel(get()) }
    }
