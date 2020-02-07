package com.github.vmas.beermeter.screen

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 07-02-2020.
 */
object BindingAdapters {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun ImageView.setImageUrl(url: String?) {
        Glide.with(this)
            .load(url)
            .into(this)
    }
}
