package com.example.sweetgram.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import pl.aprilapps.easyphotopicker.ChooserType
import pl.aprilapps.easyphotopicker.EasyImage
import pl.aprilapps.easyphotopicker.MediaFile
import pl.aprilapps.easyphotopicker.MediaSource

class ImagePickerActivity: AppCompatActivity() {
    lateinit var easyImage: EasyImage

    companion object{
        val IMAGE_PATH_KEY = "image_path"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        easyImage =
            EasyImage.Builder(this) // Chooser only
                // Will appear as a system chooser title, DEFAULT empty string
                //.setChooserTitle("Pick media")
                // Will tell chooser that it should show documents or gallery apps
                .setChooserType(ChooserType.CAMERA_AND_DOCUMENTS)
                //.setChooserType(ChooserType.CAMERA_AND_GALLERY)
                // Setting to true will cause taken pictures to show up in the device gallery, DEFAULT false
                .setCopyImagesToPublicGalleryFolder(true) // Sets the name for images stored if setCopyImagesToPublicGalleryFolder = true
                .setFolderName("Sweetgram") // Allow multiple picking
                .allowMultiple(false)
                .build()

        easyImage.openChooser(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        easyImage.handleActivityResult(requestCode, resultCode, data, this, object : EasyImage.Callbacks{
            override fun onCanceled(source: MediaSource) {
                setResult(Activity.RESULT_CANCELED)
                finish()
            }

            override fun onImagePickerError(error: Throwable, source: MediaSource) {
                error.printStackTrace()
                finish()
            }

            override fun onMediaFilesPicked(imageFiles: Array<MediaFile>, source: MediaSource) {
                Log.e("OnMediaFilesPicked", "Path = ${imageFiles[0].file.absolutePath}")
                val intent = Intent()
                intent.putExtra(IMAGE_PATH_KEY, imageFiles[0].file.absolutePath)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }

        })
    }
}