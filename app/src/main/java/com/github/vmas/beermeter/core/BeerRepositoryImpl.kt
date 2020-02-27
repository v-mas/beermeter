package com.github.vmas.beermeter.core

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.vmas.beermeter.core.model.Beer
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference
import java.util.concurrent.Executor

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

    override fun getBeer(name: String): MutableLiveData<Beer>? {
        concreteObservers[name]?.get()?.takeIf { store.beerExists(name) }?.let { return it }

        val beer = store.getBeer(name) ?: return null
        val liveData: MutableLiveData<Beer> = SavingMutableLiveData(beer, ::saveBeer)
        concreteObservers[name] = WeakReference(liveData)
        return liveData
    }

    @SuppressLint("CheckResult")
    private fun saveBeer(old: Beer, value: Beer) {
        if (!store.beerExists(old.name)) {
            error("beer does not exist")
        }
        Completable.complete().observeOn(Schedulers.computation()).subscribe {
            if (!store.beerExists(old.name)) {
                Log.d("BeerRepository", "concurrent error, trying to save beer that was removed")
                return@subscribe
            }
            store.addOrUpdateBeer(value, old.name)
            allLiveData.get()?.run {
                postValue(this.value!!.map { if (it.name == old.name) value else it })
            }
        }
    }
}

/**
 * Instead of regular handling everything inside #setValue this implementation invoke #saveHandler right when we know about the value
 */
class SavingMutableLiveData<T: Any>(initialValue: T, private val saveHandler: (T, T) -> Unit): MutableLiveData<T>(initialValue) {

    private var settingValue: T? = null

    override fun postValue(value: T) {
        settingValue = value
        saveHandler(this.value!!, value)
        super.postValue(value)
    }

    override fun setValue(value: T) {
        if (value !== settingValue) {
            saveHandler(this.value!!, value)
        }
        settingValue = null
        super.setValue(value)
    }
}
