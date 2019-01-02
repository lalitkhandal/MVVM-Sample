package com.fact.global

import android.graphics.drawable.Drawable
import android.text.TextUtils
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.RequestOptions
import com.fact.GlideApp

/**
 * Created by Lalit Khandelwal on 11, December, 2018
 * lalitkhandelwal99@gmail.com
 */
object BindingUtils {

    /**
     *This method display image from server url
     */
    @BindingAdapter("imageUrl", "placeHolder", "errorHolder")
    @JvmStatic
    fun loadImage(view: AppCompatImageView, url: String?, placeHolder: Drawable, errorHolder: Drawable) {
        if (TextUtils.isEmpty(url))
            view.setImageDrawable(placeHolder)
        else
            GlideApp.with(view.context).load(url)
                .apply(RequestOptions().placeholder(placeHolder).error(errorHolder)).into(view)
    }
}