package com.github.vmas.beermeter.core

import com.github.vmas.beermeter.core.model.Beer

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
interface BeerRepository {
    fun addBeer(beer: Beer)
    fun getAll(): List<Beer>
    fun getBeer(name: String): Beer?
}
