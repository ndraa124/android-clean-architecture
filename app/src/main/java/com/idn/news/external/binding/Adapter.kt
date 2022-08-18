package com.idn.news.external.binding

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.idn.news.R

@BindingAdapter("loadImage")
fun loadImage(imgView: ImageView, url: String?) {
    Glide.with(imgView.context)
        .asBitmap()
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(object : CustomTarget<Bitmap?>() {
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap?>?
            ) {
                imgView.setImageBitmap(resource)
                imgView.buildLayer()
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                // On Load Cleared
            }

            override fun onLoadStarted(placeholder: Drawable?) {
                imgView.setImageResource(R.mipmap.ic_loading)
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                imgView.setImageResource(R.mipmap.ic_image)
            }
        })
}
