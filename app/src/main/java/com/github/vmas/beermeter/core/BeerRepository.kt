package com.github.vmas.beermeter.core

import androidx.lifecycle.LiveData
import com.github.vmas.beermeter.core.model.Beer

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
interface BeerRepository {
    fun addBeer(beer: Beer)
    fun updateBeer(beer: Beer)
    fun removeBeer(beer: Beer)
    fun getAll(): LiveData<List<Beer>>
    fun getBeer(name: String): LiveData<Beer>?
    fun changeBeerName(oldName: String, newName: String)
}
