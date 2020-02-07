package com.github.vmas.beermeter.arch.rx

import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer

/*
 * Created by Sławomir Golonka @ ConciseSoftware on 07-02-2020.
 */

fun Disposable.disposeWith(container: DisposableContainer) {
    container.add(this)
}
