package com.kolis.cookingbook.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(entity: RecipeEntity): Long

    @Delete
    suspend fun deleteRecipe(entity: RecipeEntity)

    @Query(value = "SELECT * FROM ${RecipeEntity.TABLE_NAME}")
    fun getAllRecipes(): LiveData<List<RecipeEntity>>

}