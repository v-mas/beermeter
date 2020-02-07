package com.github.vmas.beermeter.screen.beerlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.vmas.beermeter.arch.rx.ColdPublishSubject
import com.github.vmas.beermeter.core.BeerRepository
import com.github.vmas.beermeter.core.model.Beer
import io.reactivex.Observable

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
class BeerListViewModel(private val beerRepository: BeerRepository) : ViewModel() {

    private val _beers = MutableLiveData<List<Beer>>(beerRepository.getAll())
    val beers: LiveData<List<Beer>> get() = _beers

    private val _actionOpenBeerDetails = ColdPublishSubject.create<Beer>()
    val actionOpenBeerDetails: Observable<Beer> get() = _actionOpenBeerDetails

    fun onBeerSelect(beer: Beer) {
        _actionOpenBeerDetails.onNext(beer)
    }
}
