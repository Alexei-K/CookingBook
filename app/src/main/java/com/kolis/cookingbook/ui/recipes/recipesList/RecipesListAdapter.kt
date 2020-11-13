package com.kolis.cookingbook.ui.recipes.recipesList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.kolis.cookingbook.R
import com.kolis.cookingbook.ui.recipes.RecipeModel
import com.kolis.cookingbook.utils.PhotoUploader
import kotlinx.android.synthetic.main.recipe_item.view.*
import kotlin.reflect.KFunction0

class RecipesListAdapter(
    private val onRecipeClicked: (RecipeModel) -> Unit,
    private val onActionModeUpdate: KFunction0<Unit>,
    private val showActionMode: LiveData<Boolean>
) :
    RecyclerView.Adapter<RecipesListAdapter.RecipesViewHolder>() {


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    var actionMode = false
        set(value) {
            field = value
            (showActionMode as MutableLiveData).postValue(actionMode)
        }

    var recipeList = listOf<RecipeModel>()

    var selectedList = mutableListOf<Pair<Long, RecipeModel>>()
    fun clearSelectedList(recyclerView: RecyclerView) {
        for (position in recipeList.indices) {
            recyclerView.findViewHolderForAdapterPosition(position)?.itemView?.isActivated = false
        }
        selectedList.removeAll { true }
        actionMode = false

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        return RecipesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        )

    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.bind(recipeList[position])
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }


    inner class RecipesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(recipeModel: RecipeModel) {
            val title = recipeModel.title
            itemView.recipeTitle.text = title
            //Disabled till db image saving implementation
            itemView.recipeIcon.setImageBitmap(PhotoUploader.loadImageFromStorage(recipeModel.imagePath))
            itemView.recipeTimeText.text =
                itemView.context.getString(R.string.minutes, recipeModel.cookTime)
            itemView.setOnClickListener {
                if (actionMode) {
                    if (selectedList.contains(Pair(itemId, recipeModel))) {
                        selectedList.remove(Pair(itemId, recipeModel))
                        itemView.isActivated = false
                        onActionModeUpdate.invoke()
                        if (selectedList.isEmpty()) actionMode = false

                    } else {
                        selectedList.add(Pair(itemId, recipeModel))
                        onActionModeUpdate.invoke()
                        itemView.isActivated = true
                    }
                } else {
                    onRecipeClicked(recipeModel)
                }
            }

            itemView.setOnLongClickListener {
                if (actionMode) {
                } else {
                    selectedList.add(Pair(itemId, recipeModel))
                    actionMode = true
                    itemView.isActivated = true
                    onActionModeUpdate.invoke()

                }
                true
            }

        }
    }
}