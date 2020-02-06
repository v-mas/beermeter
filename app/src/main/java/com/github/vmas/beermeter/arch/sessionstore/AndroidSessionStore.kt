package com.github.vmas.beermeter.arch.sessionstore

import android.os.Bundle

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
interface AndroidSessionStore {
    fun saveState(outState: Bundle)
    fun restoreState(savedState: Bundle)
}
