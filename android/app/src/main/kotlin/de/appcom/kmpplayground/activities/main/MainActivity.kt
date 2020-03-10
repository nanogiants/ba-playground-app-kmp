package de.appcom.kmpplayground.activities.main

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import de.appcom.kmpplayground.R
import de.appcom.kmpplayground.activities.base.BaseActivity
import de.appcom.kmpplayground.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setUpBottomNavigation()
    setUpToolbar()
  }

  override fun contentView(): View {
    binding = ActivityMainBinding.inflate(layoutInflater)
    return binding.root
  }

  private fun setUpBottomNavigation() {
    val navController = Navigation.findNavController(this, R.id.main_navhostfragment)
    NavigationUI.setupWithNavController(binding.mainBottomNavigation, navController)
  }

  private fun setUpToolbar() {
    setSupportActionBar(binding.mainToolbar)
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