package com.kolis.cookingbook.ui.welcome

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.kolis.cookingbook.MainActivity

abstract class BaseToolbarFragment : Fragment() {
    abstract fun onMenuClickListener(item: MenuItem): Boolean
    abstract val menuId: Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).changeToolbar(menuId)
        (requireActivity() as MainActivity).setOnMenuClickListener(::onMenuClickListener)

    }


}