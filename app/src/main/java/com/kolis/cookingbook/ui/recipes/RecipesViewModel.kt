package com.kolis.cookingbook.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.kolis.cookingbook.db.RecipesDatabase
import com.kolis.cookingbook.utils.AppState

class RecipesViewModel : ViewModel() {
    fun onCreateRecipe(navController: NavController) {

        navController.navigate(RecipesFragmentDirections.actionRecipesListToCreateRecipeFragment())
//        GlobalScope.launch(Dispatchers.IO) {
//            db.recipeDao().insertRecipe(RecipeEntity(0L, "Burger", 10L))
//            val size = db.recipeDao().getAllRecipes().size
//            withContext(Dispatchers.Main) {
//                ToastMaker.showLong("Replace with your own action $size")
//            }
//        }

    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    val db: RecipesDatabase = RecipesDatabase.getDatabase(AppState.appContext)
    val recipesLiveData = db.recipeDao().getAllRecipes()
}