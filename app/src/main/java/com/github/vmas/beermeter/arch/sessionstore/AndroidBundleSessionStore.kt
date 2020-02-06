package com.github.vmas.beermeter.arch.sessionstore

import android.os.Bundle

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
open class AndroidBundleSessionStore :
    AndroidSessionStore {
    protected val bundle = Bundle()

    protected open val storeKey: String = this::class.java.simpleName

    override fun saveState(outState: Bundle) {
        outState.putBundle(storeKey, this.bundle)
    }

    override fun restoreState(savedState: Bundle) {
        this.bundle.putAll(savedState.getBundle(storeKey))
    }
}
