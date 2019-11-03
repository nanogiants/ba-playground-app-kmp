package de.appcom.kmpplayground.activities.main

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import de.appcom.kmpplayground.R
import de.appcom.kmpplayground.activities.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.main_bottom_navigation
import kotlinx.android.synthetic.main.activity_main.main_toolbar

class MainActivity : BaseActivity(R.layout.activity_main) {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setUpBottomNavigation()
    setUpToolbar()
  }

  private fun setUpBottomNavigation() {
    val navController = Navigation.findNavController(this, R.id.main_navhostfragment)
    NavigationUI.setupWithNavController(main_bottom_navigation, navController)
  }

  private fun setUpToolbar() {
    setSupportActionBar(main_toolbar)
  }

  fun configureToolbar(showUpNavigation: Boolean = false, @StringRes titleRes: Int) {
    supportActionBar?.setDisplayHomeAsUpEnabled(showUpNavigation)
    supportActionBar?.setDisplayShowHomeEnabled(showUpNavigation)
    supportActionBar?.title = getString(titleRes)
  }

  fun configureCustomToolbar(@DrawableRes navigationIconRes: Int, @StringRes titleRes: Int) {
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setHomeAsUpIndicator(navigationIconRes)
    supportActionBar?.title = getString(titleRes)
  }
}