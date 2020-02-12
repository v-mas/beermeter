package com.github.vmas.beermeter.screen.beerdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.vmas.beermeter.R
import com.github.vmas.beermeter.arch.livedata.toggle
import com.github.vmas.beermeter.arch.ui.BaseViewModel
import com.github.vmas.beermeter.core.BeerRepository
import com.github.vmas.beermeter.core.error.Error
import com.github.vmas.beermeter.core.model.Beer

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
class BeerDetailsViewModel(private val beerArg: Beer, private val beerRepository: BeerRepository) : BaseViewModel() {

    val beer: LiveData<Beer>

    private val _editEnabled = MutableLiveData<Boolean>(false)
    val editEnabled: LiveData<Boolean> get() = _editEnabled

    init {
        val ld = beerRepository.getBeer(beerArg.name)
        if (ld == null) {
            beer = MutableLiveData()
            _navBack.onNext(Error.Resource(R.string.error_beer_not_exist))
        } else {
            beer = ld
        }
    }


    fun onEditClick() {
        _editEnabled.toggle()
    }

    fun onEditPhotoClick() {

    }
}

