package com.github.vmas.beermeter.screen

import android.text.InputType
import android.transition.Fade
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.bumptech.glide.Glide
import java.math.BigDecimal
import java.text.NumberFormat

/*
 * Created by Sławomir Golonka @ ConciseSoftware on 07-02-2020.
 */

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    Glide.with(this)
        .load(url)
        .into(this)
}

@set:BindingAdapter("android:visibility")
var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(visible) {
        TransitionManager.beginDelayedTransition(parent as ViewGroup, Fade())
        visibility = if (visible) View.VISIBLE else View.GONE
    }

@set:BindingAdapter("android:text")
@get:InverseBindingAdapter(attribute = "android:text", event = "android:textAttrChanged")
var TextView.decimal: BigDecimal
    get() {
        return try {
            BigDecimal(text.toString())
        } catch (e: Exception) {
            BigDecimal.ZERO
        }
    }
    set(value) {
        if (decimal != value) {
            text = NumberFormat.getInstance().apply {
                this.maximumFractionDigits = 1
                this.minimumFractionDigits = 1
            }.format(value)
        }
    }

@set:BindingAdapter("clickthrough")
var TextView.clickthrough: Boolean
    get() {
        return !isEnabled && !isFocusable && !isClickable && !isLongClickable
    }
    set(value) {
        if (value) {
            isEnabled = false
            isFocusable = false
            isClickable = false
            isLongClickable = false
        } else {
            isEnabled = true
            isFocusableInTouchMode = true
            isClickable = true
            isLongClickable = true
        }
    }

@BindingAdapter("forceImeAction")
fun TextView.forceImeAction(force: Boolean = true) {
    if (force) {
        setRawInputType(InputType.TYPE_CLASS_TEXT)
    }
}
