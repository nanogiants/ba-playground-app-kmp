package de.appcom.kmpplayground.activities.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import de.appcom.kmpplayground.R
import de.appcom.kmpplayground.activities.base.BaseActivity
import de.appcom.kmpplayground.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var appBarConfiguration: AppBarConfiguration

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setUpNavigation()
  }

  override fun contentView(): View {
    binding = ActivityMainBinding.inflate(layoutInflater)
    return binding.root
  }

  private fun setUpNavigation() {
    val navController = findNavController(this, R.id.main_navhostfragment)
    appBarConfiguration = AppBarConfiguration(navController.graph)
    binding.mainToolbar.setupWithNavController(navController, appBarConfiguration)
    setSupportActionBar(binding.mainToolbar)
    setupActionBarWithNavController(navController, appBarConfiguration)
  }

  override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(this, R.id.main_navhostfragment)
    return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val navController = findNavController(this, R.id.main_navhostfragment)
    return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.menu_main, menu)
    return true
  }
}