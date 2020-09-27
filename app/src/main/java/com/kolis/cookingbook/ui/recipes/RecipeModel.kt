package com.kolis.cookingbook.ui.recipes

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeModel(val title: String, val imagePath: String, val cookTime: Int) : Parcelable {


}