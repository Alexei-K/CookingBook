package com.kolis.cookingbook.ui.createRecipe

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kolis.cookingbook.R
import com.kolis.cookingbook.ui.recipes.RecipeModel
import com.kolis.cookingbook.utils.PhotoUploader
import com.kolis.cookingbook.utils.ToastMaker
import kotlinx.android.synthetic.main.fragment_create_recipe.*


class CreateRecipeFragment : Fragment() {

    companion object {
        val TAKE_PHOTO: Int = 1473
    }

    var recipeModel = RecipeModel.empty_model.copy()
    val viewModel = CreateRecipeViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_create_recipe, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        recipeWatchImage.setOnClickListener {
            viewModel.onChoosePhotoClick(this)
        }
        recipeWatchTitle.doOnTextChanged { text, _, _, _ ->
            recipeModel = recipeModel.copy(title = text.toString())
            ToastMaker.showLong("New title = $text")
        }

        recipeWatchTimeText.doOnTextChanged { text, _, _, _ ->
            recipeModel = recipeModel.copy(cookTime = text.toString().toInt())
            ToastMaker.showLong("New time = $text")
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.idLiveData.observe(viewLifecycleOwner, Observer {
            recipeModel = recipeModel.copy(id = it)
            viewModel.idLiveData.removeObservers(viewLifecycleOwner)
        })
        viewModel.saveRecipe(requireContext(), recipeModel)

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
                if (data == null) return
                val imagePath = PhotoUploader.savePhotoInternalStorage(data!!.data!!, requireActivity())
                ToastMaker.showLong("absolute path of selected image = $imagePath")
                recipeModel = recipeModel.copy(imagePath = imagePath.toString())
                recipeWatchImage.setImageBitmap(PhotoUploader.loadImageFromStorage(recipeModel.imagePath))
            }

        }

    }


}