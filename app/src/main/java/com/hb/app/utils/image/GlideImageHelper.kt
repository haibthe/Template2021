package com.hb.app.utils.image

import android.content.Context
import android.graphics.Bitmap
import android.webkit.URLUtil
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.hb.app.R
import com.hb.base.utils.ImageHelper
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named

class GlideImageHelper : ImageHelper, KoinComponent {

    private val token: String by inject(named("token"))

    private val options: RequestOptions = RequestOptions()
        .fitCenter()
        .placeholder(R.drawable.no_image)
        .error(R.drawable.no_image)
        .priority(Priority.HIGH)
        .timeout(10_000)
        .skipMemoryCache(false)
        .diskCacheStrategy(DiskCacheStrategy.NONE)

    override fun loadImage(view: ImageView, imageData: Any) {
        val temp = imageData.toString()
        if (URLUtil.isValidUrl(temp)) {
            Glide.with(view)
                .load(getUrlWithHeaders(temp, token))
                .apply(options)
                .into(view)
        } else {
            Glide.with(view)
                .load(imageData)
                .apply(options)
                .into(view)
        }
    }

    override fun getBitmap(context: Context, imageData: Any): Bitmap {
        val temp = imageData.toString()
        return if (URLUtil.isValidUrl(temp)) {
            Glide.with(context).asBitmap()
                .load(getUrlWithHeaders(temp, token))
                .addListener(object : RequestListener<Bitmap> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                })
                .submit()
                .get()
        } else {
            Glide.with(context).asBitmap()
                .load(imageData)
                .submit()
                .get()
        }
    }

    private fun getUrlWithHeaders(url: String, token: String): GlideUrl {
        val headers = LazyHeaders.Builder()
            .addHeader("Authorization", token)
            .build()
        return GlideUrl(url, headers)
    }
}
