package com.example.marsphotos

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView,imgUrl:String?){
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        imageView.load(imgUri)
    }
}