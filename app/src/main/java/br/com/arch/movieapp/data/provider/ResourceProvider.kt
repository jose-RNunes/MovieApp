package br.com.arch.movieapp.data.provider

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes stringRest: Int): String

    fun getDrawable(@DrawableRes drawableRes: Int): Drawable?

    fun getColor(@ColorRes colorRes: Int): Int

    fun getDimension(dimenRes: Int): Float
}