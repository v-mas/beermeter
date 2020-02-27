package com.github.vmas.beermeter.core.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
@Parcelize
data class Beer(
    val name: String,
    val imgUrl: String?,
    val type: String,
    val percentage: BigDecimal,
    val country: String,
    val website: String?
) : Parcelable
