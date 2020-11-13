package com.kolis.cookingbook.ui.welcome

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.kolis.cookingbook.MainActivity
import com.kolis.cookingbook.R
import com.kolis.cookingbook.utils.AppPreffManager
import kotlinx.android.synthetic.main.activity_register.*


class StartWelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkRegistration()
        setContentView(R.layout.activity_register)
        setUpAdapter(register_viewPager)
        register_tabs.setupWithViewPager(register_viewPager)
    }

    private fun setUpAdapter(pager: ViewPager) {
        val adapter = InfoTabsAdapter(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT)
        val firstFragment = StartInfoFragment()
        setFragmentBundle(
            firstFragment, R.drawable.welcome_1,
            "", getString(R.string.welcome1)
        )
        adapter.addFragment(0, firstFragment)
        val secondFragment = StartInfoFragment()
        setFragmentBundle(
            secondFragment, R.drawable.welcome_2,
            "", getString(R.string.welcome2)
        )
        adapter.addFragment(1, secondFragment)
        val thirdFragment = StartInfoLastFragment()
        setFragmentBundle(
            thirdFragment, R.drawable.welcome_3,
            "", getString(R.string.welcome3)
        )
        adapter.addFragment(2, thirdFragment)
        pager.adapter = adapter
    }

    private fun setFragmentBundle(fragment: StartInfoFragment, imgId: Int, title: String, text: String) {
        val bundle = Bundle()
        bundle.putInt(StartInfoFragment.INFO_IMAGE, imgId)
        bundle.putString(StartInfoFragment.INFO_TITLE, title)
        bundle.putString(StartInfoFragment.INFO_TEXT, text)
        fragment.arguments = bundle
    }

    private fun checkRegistration() {
        val pref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        if (pref.getBoolean(AppPreffManager.IS_REGISTERED, false)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}