package de.appcom.kmpplayground.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import dagger.android.support.DaggerAppCompatActivity
import de.appcom.kmpplayground.R
import game.domain.Game
import game.presentation.GameView
import kotlinx.android.synthetic.main.activity_game.game_gridlayout
import kotlinx.android.synthetic.main.activity_game.game_toolbar
import javax.inject.Inject

class GameActivity : DaggerAppCompatActivity(), GameView {

  @Inject
  lateinit var game: Game

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_game)
    setUpToolbar()
  }

  private fun setUpToolbar() {
    setSupportActionBar(game_toolbar)
    supportActionBar?.title = getString(R.string.game_title)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> onBackPressed()
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onResume() {
    super.onResume()
    setUpGame()
  }

  fun setUpGame() {
    for (i in 0 until game_gridlayout.childCount) {
      val childButton = game_gridlayout.getChildAt(i)
      childButton.setOnClickListener {
        game.onNewStonePlaced(i)
      }
    }
  }

  override fun invalidateBoard(board: Array<Int>) {

    for (i in 0 until game_gridlayout.childCount) {
      val childButton = game_gridlayout.getChildAt(i)
      val backgroundColor = when (board[i]) {
        -1 -> Color.parseColor("#ED765A")
        1 -> Color.parseColor("#1a73e8")
        else -> Color.parseColor("#FFFFFF")
      }
      childButton.setBackgroundColor(backgroundColor)
    }
  }

  override fun notify(message: String) {
    AlertDialog.Builder(this).apply {
      setMessage(message)
      setTitle("Game over")
      setPositiveButton("ok", { dialog, id -> })
    }.create().show()
  }
}