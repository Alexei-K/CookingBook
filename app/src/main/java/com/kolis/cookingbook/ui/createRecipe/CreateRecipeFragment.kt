package com.kolis.cookingbook.ui.createRecipe

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kolis.cookingbook.R
import com.kolis.cookingbook.utils.ToastMaker
import kotlinx.android.synthetic.main.fragment_create_recipe.*


class CreateRecipeFragment : Fragment() {

    companion object {

        val TAKE_PHOTO: Int = 1473

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        recipesViewModel = ViewModelProviders.of(this).get(RecipesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_create_recipe, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipeWatchImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), TAKE_PHOTO)

        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        ToastMaker.showLong("2")
        if (resultCode != Activity.RESULT_OK) {
            ToastMaker.showLong("result not ok - $resultCode")
            return
        }

        when (requestCode) {
            TAKE_PHOTO -> {
                ToastMaker.showLong("3")
                if (data == null) return
                val selectedImage: Uri = data!!.data!!
                val inputStream = requireActivity().contentResolver!!.openInputStream(selectedImage)

                recipeWatchImage.setImageBitmap(BitmapFactory.decodeStream(inputStream))

//                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
//
//                val cursor: Cursor = requireActivity().contentResolver!!.query(
//                    selectedImage,
//                    filePathColumn, null, null, null
//                )!!
//                cursor?.moveToFirst()
//
//                val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
//                val picturePath: String = cursor.getString(columnIndex)
//                cursor.close()
//                ToastMaker.showLong("4")
//
//                val bitmap = BitmapFactory.decodeFile(picturePath)
//                recipeWatchImage.setImageBitmap(bitmap)

            }

        }

    }


}