package com.github.vmas.beermeter.screen

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.transition.TransitionManager
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

@set:BindingAdapter("android:visibility")
var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(visible) {
        TransitionManager.beginDelayedTransition(parent as ViewGroup)
        visibility = if (visible) View.VISIBLE else View.GONE
    }
