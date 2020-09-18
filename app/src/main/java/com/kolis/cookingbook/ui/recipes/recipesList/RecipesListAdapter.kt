package com.kolis.cookingbook.ui.recipes.recipesList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kolis.cookingbook.R
import com.kolis.cookingbook.ui.recipes.RecipeModel
import kotlinx.android.synthetic.main.recipe_item.view.*

class RecipesListAdapter : RecyclerView.Adapter<RecipesListAdapter.RecipesViewHolder>() {


    var recipeList = listOf<RecipeModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        return RecipesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        )

    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }


    inner class RecipesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {
            itemView.recipeTitle.text = recipeList[position].title
            //refactor after DB impl
            itemView.recipeIcon.setImageDrawable(itemView.context.getDrawable(recipeList[position].imagePath.toInt()))
            itemView.recipeTimeText.text =
                itemView.context.getString(R.string.minutes, recipeList[position].cookTime)
        }
    }
}