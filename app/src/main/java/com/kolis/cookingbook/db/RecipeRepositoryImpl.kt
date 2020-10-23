package com.kolis.cookingbook.db

import com.kolis.cookingbook.ui.recipes.RecipeModel
import com.kolis.cookingbook.utils.AppState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeRepositoryImpl : RecipeRepository {

    val db = RecipesDatabase.getDatabase(AppState.appContext)
    override fun insertRecipe(entity: RecipeEntity): Long {
        return 0L
    }

    override fun deleteRecipes(entity: List<RecipeModel>) {
        CoroutineScope(Dispatchers.IO).launch { db.recipeDao().deleteRecipes(entity.map { it.toEntity() }) }
    }

}