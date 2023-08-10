package br.com.arch.movieapp.data.provider

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import br.com.arch.movieapp.data.provider.ResourceProvider
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(private val context: Context) : ResourceProvider {

    override fun getString(stringRest: Int): String {
        return context.getString(stringRest)
    }

    override fun getDrawable(drawableRes: Int): Drawable? {
        return ContextCompat.getDrawable(context, drawableRes)
    }

    override fun getColor(colorRes: Int): Int {
        return ContextCompat.getColor(context, colorRes)
    }

    override fun getDimension(dimenRes: Int): Float {
        return context.resources.getDimension(dimenRes)
    }
}