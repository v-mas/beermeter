package com.github.vmas.beermeter.core

import com.github.vmas.beermeter.core.model.Beer

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
class BeerVolatileStore : BeerStore {

    private val store = mutableMapOf<String, Beer>()

    init {
        addBeer(Beer("Warka"))
        addBeer(Beer("Tyskie"))
        addBeer(Beer("EB"))
        addBeer(Beer("Heineken"))
        addBeer(Beer("Carlsberg"))
    }

    override fun addBeer(beer: Beer) {
        store[beer.name] = beer
    }

    override fun getAll(): List<Beer> {
        return store.values.toList()
    }

    override fun getBeer(name: String): Beer? {
        return store[name]
    }
}
