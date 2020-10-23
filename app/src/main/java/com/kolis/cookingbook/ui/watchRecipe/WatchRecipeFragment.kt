package com.kolis.cookingbook.ui.watchRecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.kolis.cookingbook.MainActivity
import com.kolis.cookingbook.R
import com.kolis.cookingbook.ui.recipes.RecipeModel
import com.kolis.cookingbook.ui.welcome.BaseToolbarFragment
import com.kolis.cookingbook.utils.PhotoUploader
import kotlinx.android.synthetic.main.fragment_recipe_watch.*

class WatchRecipeFragment : BaseToolbarFragment() {

    val args: WatchRecipeFragmentArgs by navArgs()
    lateinit var model: RecipeModel
    private lateinit var viewModel: WatchRecipeViewModel
    private lateinit var navController: NavController
    override fun onMenuClickListener(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                navController.navigate(
                    WatchRecipeFragmentDirections.actionRecipeWatchToCreateRecipeFragment(
                        model
                    )
                )
                true
            }
            else -> false
        }
    }

    override val menuId: Int = R.menu.watch_recipe_menu

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_recipe_watch, container, false)
        model = args.recipeModel
        viewModel = ViewModelProvider(this).get(WatchRecipeViewModel::class.java)
        navController = requireActivity().findNavController(R.id.nav_host_fragment)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).changeToolbar(R.menu.watch_recipe_menu)
        recipeWatchImage.setImageBitmap(PhotoUploader.loadImageFromStorage(model.imagePath))
        recipeWatchTitle.text = model.title
        recipeWatchTimeText.text = view.context.getString(R.string.minutes, model.cookTime)
    }

}