package com.github.vmas.beermeter.arch.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.github.vmas.beermeter.arch.app.BeerApp

/*
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */

val Fragment.dependencyContainer: DependencyContainer
    get() = requireContext().dependencyContainer

val AppCompatActivity.dependencyContainer: DependencyContainer
    get() = (application as BeerApp).dependencyContainer

val Context.dependencyContainer: DependencyContainer
    get() = (applicationContext as BeerApp).dependencyContainer
