package com.github.vmas.beermeter.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.vmas.beermeter.core.model.Beer
import java.lang.ref.WeakReference

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
class BeerRepositoryImpl(private val store: BeerStore) : BeerRepository {

    private var allLiveData = WeakReference<MutableLiveData<List<Beer>>>(null)
    private val concreteObservers: MutableMap<String, WeakReference<MutableLiveData<Beer>>> = hashMapOf()

    override fun addBeer(beer: Beer) {
        if (store.beerExists(beer.name)) {
            error("beer already exist")
        }
        store.addOrUpdateBeer(beer)
        allLiveData.get()?.run {
            postValue(value!! + beer)
        }
    }

    override fun updateBeer(beer: Beer) {
        if (!store.beerExists(beer.name)) {
            error("beer does not exist")
        }
        store.addOrUpdateBeer(beer)
        allLiveData.get()?.run {
            postValue(value!!.map { if (it.name == beer.name) beer else it })
        }
        concreteObservers[beer.name]?.get()?.postValue(beer)
    }

    override fun removeBeer(beer: Beer) {
        store.removeBeer(beer.name)
        allLiveData.get()?.run {
            postValue(value!!.filter { it.name != beer.name })
        }
        concreteObservers.remove(beer.name)
    }

    override fun changeBeerName(oldName: String, newName: String) {
        val beer = store.getBeer(oldName) ?: error("beer does not exist")
        val newBeer = beer.copy(name = newName)
        store.addOrUpdateBeer(name = oldName, beer = newBeer)

        allLiveData.get()?.run {
            postValue(value!!.map { if (it.name == oldName) newBeer else it })
        }
    }

    override fun getAll(): LiveData<List<Beer>> {
        return allLiveData.get() ?: MutableLiveData(store.getAll()).also { allLiveData = WeakReference(it) }
    }

    override fun getBeer(name: String): LiveData<Beer>? {
        concreteObservers[name]?.get()?.takeIf { store.beerExists(name) }?.let { return it }

        val beer = store.getBeer(name) ?: return null
        val liveData = MutableLiveData(beer)
        concreteObservers[name] = WeakReference(liveData)
        return liveData
    }
}
