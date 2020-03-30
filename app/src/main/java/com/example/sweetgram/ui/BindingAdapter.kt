package com.example.sweetgram.ui

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingComponent
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.io.File

object BindingAdapter: DataBindingComponent {

    /*val BASE_URL = MainActivity.getString(
        R.string.base_weather_url
    )+"img/wn/"*/

    fun getBindingAdapter(): com.example.sweetgram.ui.BindingAdapter {
        return this
    }

    @JvmStatic
    @BindingAdapter(value = ["app:iconId"], requireAll = true)
    fun loadImage(view: View, iconId: String?) {
        if(!iconId.isNullOrBlank()) {
            Log.i("Picasso", "Load file: $iconId")
            when (view){
                is ImageView ->  Picasso.get().load(File(iconId)).into(view)
                else -> {
                    view.tag = object: Target{
                        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                            Log.e("Picasso", "placeHolderDrawable = $placeHolderDrawable")
                        }

                        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                            Log.e("Picasso", "onBitmapFailed = $errorDrawable")
                            e?.printStackTrace()
                        }

                        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                            Log.e("Picasso", "onBitmapLoaded = $bitmap")
                            view.background = BitmapDrawable(bitmap)
                        }
                    }
                    Picasso.get().load(File(iconId)).into(view.tag as Target)
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["app:need_to_show"], requireAll = true)
    fun setVisible(view: View, needToShow: Boolean?) {
        needToShow?.let {
            if (it){
                view.visibility = View.VISIBLE
            }
            else{
                view.visibility = View.INVISIBLE
            }
        }
    }
}