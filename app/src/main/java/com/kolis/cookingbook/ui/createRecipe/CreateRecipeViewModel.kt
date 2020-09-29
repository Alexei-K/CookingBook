package com.kolis.cookingbook.ui.createRecipe

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kolis.cookingbook.db.RecipesDatabase
import com.kolis.cookingbook.ui.recipes.RecipeModel
import com.kolis.cookingbook.utils.ToastMaker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CreateRecipeViewModel : ViewModel() {
    fun onChoosePhotoClick(fragment: Fragment) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        fragment.startActivityForResult(Intent.createChooser(intent, "Select Picture"), CreateRecipeFragment.TAKE_PHOTO)
    }

    val idLiveData: MutableLiveData<Long> = MutableLiveData()

    fun saveRecipe(context: Context, recipeModel: RecipeModel) {
        ToastMaker.showLong("Saving to database $recipeModel")
        CoroutineScope(IO).launch {
            idLiveData.postValue(RecipesDatabase.getDatabase(context).recipeDao().insertRecipe(recipeModel.toEntity()))
        }
    }
}