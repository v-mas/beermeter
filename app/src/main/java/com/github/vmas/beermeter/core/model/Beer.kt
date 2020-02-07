package com.github.vmas.beermeter.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
@Parcelize
data class Beer(
    val name: String,
    val imgUrl: String?,
    val type: String,
    val percentage: Float,
    val country: String,
    val website: String?
) : Parcelable
