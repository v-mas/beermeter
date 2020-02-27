package com.github.vmas.beermeter.screen.beerdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.vmas.beermeter.R
import com.github.vmas.beermeter.arch.livedata.toggle
import com.github.vmas.beermeter.arch.rx.ColdPublishSubject
import com.github.vmas.beermeter.arch.ui.BaseViewModel
import com.github.vmas.beermeter.core.BeerRepository
import com.github.vmas.beermeter.core.error.Error
import com.github.vmas.beermeter.core.model.Beer
import io.reactivex.Observable
import java.math.BigDecimal

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
class BeerDetailsViewModel(private val beerArg: Beer, private val beerRepository: BeerRepository) : BaseViewModel() {

    private val _beer: MutableLiveData<Beer>
    val beer: LiveData<Beer> get() = _beer

    init {
        val ld = beerRepository.getBeer(beerArg.name)
        if (ld == null) {
            _beer = MutableLiveData()
            _navBack.onNext(Error.Resource(R.string.error_beer_not_exist))
        } else {
            _beer = ld
        }
    }

    private val _editEnabled = MutableLiveData<Boolean>(false)
    val editEnabled: LiveData<Boolean> get() = _editEnabled

    private val _eventOpenBrowser = ColdPublishSubject.create<String>()
    val eventOpenBrowser: Observable<String> get() = _eventOpenBrowser

    val name = MutableLiveData<String>(beer.value?.name ?: "")
    val type = MutableLiveData<String>(beer.value?.type ?: "")
    val alcoholContent = MutableLiveData<BigDecimal>(beer.value?.percentage ?: BigDecimal.ZERO)
    val country = MutableLiveData<String>(beer.value?.country ?: "")
    val website = MutableLiveData<String>(beer.value?.website ?: "")

    fun onWebsiteClick() {
        val website = beer.value!!.website ?: return
        _eventOpenBrowser.onNext(website)
    }

    fun onEditClick() {
        if (editEnabled.value!!) {
            _beer.value = _beer.value!!.copy(
                name = name.value!!,
                type = type.value!!,
                percentage = alcoholContent.value!!,
//                imgUrl =
                country = country.value!!,
                website = website.value!!
            )
        }
        _editEnabled.toggle()
    }

    fun onEditPhotoClick() {

    }
}
