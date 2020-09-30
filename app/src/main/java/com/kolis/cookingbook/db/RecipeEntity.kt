package com.kolis.cookingbook.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kolis.cookingbook.db.RecipeEntity.Companion.TABLE_NAME
import com.kolis.cookingbook.ui.recipes.RecipeModel

@Entity(tableName = TABLE_NAME)
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0L,
    val title: String,
    val time: Long,
    val imagePath: String
) {

    companion object {
        const val TABLE_NAME = "recipe_table"
    }

    fun toModel(): RecipeModel {
        return RecipeModel(id, title, imagePath, time.toInt())
    }
}