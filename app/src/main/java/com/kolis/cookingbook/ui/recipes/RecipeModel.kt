package com.kolis.cookingbook.ui.recipes

import android.os.Parcelable
import com.kolis.cookingbook.db.RecipeEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeModel(val id: Long = 0L, val title: String, val imagePath: String, val cookTime: Int) : Parcelable {

    companion object {
        val empty_model = RecipeModel(0L, "", "", 0)
    }

    fun toEntity(): RecipeEntity {
        return RecipeEntity(id, title, cookTime.toLong())
    }

}