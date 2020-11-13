package com.kolis.cookingbook.ui.recipes

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kolis.cookingbook.R
import com.kolis.cookingbook.ui.recipes.recipesList.RecipesListAdapter
import com.kolis.cookingbook.ui.welcome.BaseToolbarFragment
import com.kolis.cookingbook.utils.ToastMaker
import kotlinx.android.synthetic.main.fragment_recipes.*

class RecipesFragment : BaseToolbarFragment(), ActionMode.Callback {
    private lateinit var navController: NavController
    private lateinit var recipesViewModel: RecipesViewModel
    private var actionMode: ActionMode? = null

    private lateinit var listadapter: RecipesListAdapter
    private val sampleRecipeModel = listOf(
        RecipeModel(0L, "CheeseBurger", R.drawable.burger_example.toString(), 60),
        RecipeModel(0L, "porridge", R.drawable.porridge.toString(), 30),
        RecipeModel(0L, "Pilaf", R.drawable.pilaf.toString(), 90)
    )

    override val menuId: Int
        get() = R.menu.main_menu

    override fun onMenuClickListener(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                ToastMaker.showLong("settings pressed")
                true
            }
            else -> false
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recipesViewModel = ViewModelProviders.of(this).get(RecipesViewModel::class.java)
        listadapter = RecipesListAdapter(::onRecipeClicked, ::onActionModeUpdate, recipesViewModel.showActionMode)
        val root = inflater.inflate(R.layout.fragment_recipes, container, false)
        recipesViewModel.showActionMode.observe(viewLifecycleOwner, Observer {
            if (it && actionMode == null) {
                actionMode = (activity as AppCompatActivity).startSupportActionMode(this)
            } else {
                actionMode?.finish()
            }
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = NavHostFragment.findNavController(this)
        initRecycleView()
        fab.setOnClickListener {
            recipesViewModel.onCreateRecipe(navController)
        }
    }

    private fun initRecycleView() {
        recipesRecycleView.layoutManager = LinearLayoutManager(context)
        recipesViewModel.recipesLiveData.observe(viewLifecycleOwner, Observer { list ->
            listadapter.recipeList = list.map { it.toModel() }
            listadapter.notifyDataSetChanged()
        })
        recipesRecycleView.adapter = listadapter
    }

    private fun onRecipeClicked(model: RecipeModel) {
        navController.navigate(RecipesFragmentDirections.actionRecipesListToWatchRecipeFragment(model))
    }


    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.delete_recipes -> {
                recipesViewModel.deleteRecipes(listadapter.selectedList.map { it.second })
                onDestroyActionMode(mode)
            }
        }
        return true
    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        with(requireActivity()) {
            mode?.title = resources.getQuantityString(
                R.plurals.recipes_selected, listadapter.selectedList.size,
                listadapter.selectedList.size
            )
            menuInflater.inflate(R.menu.recipes_action_mode, menu)
        }
        return true
    }

    private fun onActionModeUpdate() {
        actionMode?.title = resources.getQuantityString(
            R.plurals.recipes_selected, listadapter.selectedList.size,
            listadapter.selectedList.size
        )
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean = false

    override fun onDestroyActionMode(mode: ActionMode?) {
        actionMode = null
        recipesRecycleView?.apply { listadapter.clearSelectedList(this) }
        mode?.finish()
    }

    override fun onPause() {
        super.onPause()
        onDestroyActionMode(actionMode)
    }
}