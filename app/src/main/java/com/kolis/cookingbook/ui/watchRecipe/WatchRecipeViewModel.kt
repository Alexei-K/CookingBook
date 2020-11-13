package com.kolis.cookingbook.ui.watchRecipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.kolis.cookingbook.ui.recipes.RecipeModel

class WatchRecipeViewModel : ViewModel() {
    fun onEditRecipeClicked(navController: NavController, model: RecipeModel) {
//        navController.navigate(RecipesFragmentDirections.actionRecipesListToCreateRecipeFragment())
    }

    fun deleteRecipe(model: RecipeModel) {
//        RecipeRepositoryImpl().deleteRecipes(models)
    }

    private val _showActionMode = MutableLiveData<Boolean>().apply {
        value = false
    }
    val showActionMode: LiveData<Boolean> = _showActionMode
}