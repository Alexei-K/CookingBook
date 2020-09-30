package com.kolis.cookingbook.db

interface RecipeRepository {

    fun insertRecipe(entity: RecipeEntity): Long

}