package com.github.vmas.beermeter.core.error

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 10-02-2020.
 */
sealed class Error {
    class Text(val message: String) : Error()
    class Resource(val stringRes: Int) : Error()
}
