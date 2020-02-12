package com.github.vmas.beermeter.core

import com.github.vmas.beermeter.core.model.Beer

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
interface BeerStore {
    fun addOrUpdateBeer(beer: Beer, name: String = beer.name)
    fun removeBeer(name: String)
    fun getAll(): List<Beer>
    fun getBeer(name: String): Beer?
    fun beerExists(name: String): Boolean
}
