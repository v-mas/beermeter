package com.github.vmas.beermeter.core

import android.content.SharedPreferences
import androidx.core.content.edit
import com.github.vmas.beermeter.core.model.Beer
import com.google.gson.Gson

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
class BeerSharedPrefsStore(private val sharedPrefs: SharedPreferences, private val gson: Gson) : BeerStore {

    override fun addBeer(beer: Beer) {
        sharedPrefs.edit {
            putString(beer.name, gson.toJson(beer))
        }
    }

    override fun getAll(): List<Beer> {
        return sharedPrefs.all.map { gson.fromJson(it.value.toString(), Beer::class.java) }
    }

    override fun getBeer(name: String): Beer? {
        return sharedPrefs.getString(name, null)?.let { gson.fromJson(it, Beer::class.java) }
    }
}
