package com.example.sweetgram.ui

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingComponent

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
        //todo set card h and w to 40dp
        /*if(iconId!=null && !iconId.isBlank() && size!=null && size > 0) {
            val sizeStr = if (size == 1){
                ""
            }
            else{
                "@$size" + "x"
            }
            val uri = Uri.parse("${BASE_URL}$iconId$sizeStr.png")
            Log.i("Picasso", "Load file: " + "${BASE_URL}$iconId$sizeStr.png")
            //Picasso.get().isLoggingEnabled = true
            Picasso.get().load(uri).into(view)
        }*/
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