package com.kolis.cookingbook.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kolis.cookingbook.R
import com.kolis.cookingbook.ui.recipes.recipesList.RecipesListAdapter
import kotlinx.android.synthetic.main.fragment_recipes.*

class RecipesFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var recipesViewModel: RecipesViewModel
    private val listadapter: RecipesListAdapter = RecipesListAdapter(::onRecipeClicked)
    private val sampleRecipeModel = listOf(
        RecipeModel("CheeseBurger", R.drawable.burger_example.toString(), 60),
        RecipeModel("porridge", R.drawable.porridge.toString(), 30),
        RecipeModel("Pilaf", R.drawable.pilaf.toString(), 90)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recipesViewModel = ViewModelProviders.of(this).get(RecipesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_recipes, container, false)
        recipesViewModel.text.observe(viewLifecycleOwner, Observer {

        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = NavHostFragment.findNavController(this)
        initRecycleView()
    }

    private fun initRecycleView() {
        recipesRecycleView.layoutManager = LinearLayoutManager(context)
        listadapter.recipeList = sampleRecipeModel
        recipesRecycleView.adapter = listadapter
    }

    private fun onRecipeClicked(model: RecipeModel) {
//        val action = RecipesDirection.action_recipesList_to_watchRecipeFragment()
//        action.recipeModel = model
//        navController.navigate(action)

    }
}