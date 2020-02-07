package com.github.vmas.beermeter.screen

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/*
 * Created by Sławomir Golonka @ ConciseSoftware on 07-02-2020.
 */

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    Glide.with(this)
        .load(url)
        .into(this)
}

@BindingAdapter("textPercentage")
fun TextView.setPercentageText(percentage: Float) {
    text = String.format("%.2f%%", percentage)
}
