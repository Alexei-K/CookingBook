package com.kolis.cookingbook.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.kolis.cookingbook.db.RecipeRepositoryImpl
import com.kolis.cookingbook.db.RecipesDatabase
import com.kolis.cookingbook.utils.AppState

class RecipesViewModel : ViewModel() {
    fun onCreateRecipe(navController: NavController) {
        navController.navigate(RecipesFragmentDirections.actionRecipesListToCreateRecipeFragment())
    }

    fun deleteRecipes(models: List<RecipeModel>) {
        RecipeRepositoryImpl().deleteRecipes(models)
    }

    private val _showActionMode = MutableLiveData<Boolean>().apply {
        value = false
    }
    val showActionMode: LiveData<Boolean> = _showActionMode
    val db: RecipesDatabase = RecipesDatabase.getDatabase(AppState.appContext)
    val recipesLiveData = db.recipeDao().getAllRecipes()
}