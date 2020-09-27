package com.kolis.cookingbook.ui.recipes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kolis.cookingbook.db.RecipeEntity
import com.kolis.cookingbook.db.RecipesDatabase
import com.kolis.cookingbook.utils.AppState
import com.kolis.cookingbook.utils.ToastMaker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipesViewModel : ViewModel() {
    fun onCreateRecipe() {


        GlobalScope.launch(Dispatchers.IO) {
            db.recipeDao().insertRecipe(RecipeEntity(0L, "Burger", 10L))
            val size = db.recipeDao().getAllRecipes().size
            withContext(Dispatchers.Main) {
                ToastMaker.showLong("Replace with your own action $size")
            }
        }

    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    val db: RecipesDatabase

    init {
        db = RecipesDatabase.getDatabase(AppState.appContext)
    }
}