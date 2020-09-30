package com.kolis.cookingbook.utils

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import androidx.core.net.toUri
import androidx.fragment.app.FragmentActivity
import com.kolis.cookingbook.R
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


class PhotoUploader {

    companion object {
        fun loadImageFromStorage(stringUri: String): Bitmap {
            try {
                val uri = Uri.parse(stringUri)

                return MediaStore.Images.Media.getBitmap(AppState.appContext.contentResolver, uri)

            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                return BitmapFactory.decodeResource(AppState.appContext.resources, R.drawable.upload_photo)
            }
        }

        fun savePhotoInternalStorage(uri: Uri, activity: FragmentActivity): Uri {
            val inputStream = activity.contentResolver!!.openInputStream(uri)

            val bitmapImage = BitmapFactory.decodeStream(inputStream)
            val cw = ContextWrapper(AppState.appContext)
            val directory: File = cw.getDir("imageDir", Context.MODE_PRIVATE)
            val name = "recipePhoto${System.currentTimeMillis()}.jpg"
            val mypath = File(directory, name)

            var fos: FileOutputStream? = null
            try {
                fos = FileOutputStream(mypath)
                bitmapImage.compress(Bitmap.CompressFormat.PNG, 50, fos)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    fos?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            return mypath.toUri()
        }
    }
}