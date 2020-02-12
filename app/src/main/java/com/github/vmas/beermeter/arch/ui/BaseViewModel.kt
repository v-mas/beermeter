package com.github.vmas.beermeter.arch.ui

import androidx.lifecycle.ViewModel
import com.github.vmas.beermeter.arch.rx.ColdPublishSubject
import com.github.vmas.beermeter.core.error.Error
import io.reactivex.Observable

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 10-02-2020.
 */
abstract class BaseViewModel : ViewModel() {
    protected val _navBack = ColdPublishSubject.create<Error>()
    val navBackEvent: Observable<Error> get() = _navBack
}
