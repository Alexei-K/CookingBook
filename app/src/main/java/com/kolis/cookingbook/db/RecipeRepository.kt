package com.kolis.cookingbook.db

import com.kolis.cookingbook.ui.recipes.RecipeModel

interface RecipeRepository {

    fun insertRecipe(entity: RecipeEntity): Long
    fun deleteRecipes(entity: List<RecipeModel>)

}