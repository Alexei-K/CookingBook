package com.kolis.cookingbook.ui.watchRecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.kolis.cookingbook.R
import com.kolis.cookingbook.ui.recipes.RecipeModel
import kotlinx.android.synthetic.main.fragment_recipe_watch.*

class WatchRecipeFragment : Fragment() {

    val args: WatchRecipeFragmentArgs by navArgs()
    lateinit var model: RecipeModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        recipesViewModel = ViewModelProviders.of(this).get(RecipesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_recipe_watch, container, false)
        model = args.recipeModel



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //            //Disabled till db image saving implementation
//        recipeWatchImage.setImageDrawable(view.context.getDrawable(model.imagePath.toInt()))
        recipeWatchTitle.text = model.title
        recipeWatchTimeText.text = view.context.getString(R.string.minutes, model.cookTime)
    }

}