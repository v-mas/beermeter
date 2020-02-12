package com.github.vmas.beermeter.arch.livedata

import androidx.lifecycle.MutableLiveData

/*
 * Created by Sławomir Golonka @ ConciseSoftware on 10-02-2020.
 */

fun MutableLiveData<Boolean>.toggle() {
    value = !value!!
}
