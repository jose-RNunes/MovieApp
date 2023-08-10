package br.com.arch.movieapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.loadImageView(url: String) {
    Glide.with(this.context).load(url).into(this)
}