/*
 *
 *  *
 *  *  *  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  *  *  * @Project:
 *  *  *  *		 VOOT
 *  *  *  * @Copyright:
 *  *  *  *     		Copyright © 2017, Viacom18 Media Private Limited. All Rights Reserved *
 *  *  *  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *  *
 *  *
 *
 */

package com.techtreeit.sample.techtree.utils;

import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

@SuppressWarnings("JavaDoc")
public class TTImageLoaderUtils {

    /**
     * For Image Views Only
     *
     * @param imageView
     * @param url
     */
    public static void setThumbnailImage(final ImageView imageView, String url, final int defaultImage) {
        Glide.with(imageView.getContext())
                .load(url)
                .priority(Priority.IMMEDIATE)
                .placeholder(defaultImage)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }



    public static void setCircularImageSmall(final ImageView imageView, String url, int defaultImage) {
        Glide.with(imageView.getContext())
                .load(url)
                .asBitmap()
                .approximate()
                .dontAnimate()
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .placeholder(defaultImage)
                .priority(Priority.IMMEDIATE)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(imageView.getContext().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }
}
