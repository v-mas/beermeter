package com.github.vmas.beermeter.core

import com.github.vmas.beermeter.core.model.Beer

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
class BeerRepositoryImpl(private val store: BeerStore) : BeerRepository {
    override fun addBeer(beer: Beer) {
        store.addBeer(beer)
    }

    override fun getAll(): List<Beer> {
        return store.getAll()
    }

    override fun getBeer(name: String): Beer? {
        return store.getBeer(name)
    }
}
