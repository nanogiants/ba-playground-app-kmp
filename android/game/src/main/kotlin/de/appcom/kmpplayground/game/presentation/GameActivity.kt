package de.appcom.kmpplayground.game.presentation

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import dagger.android.support.DaggerAppCompatActivity
import de.appcom.kmpplayground.game.R
import de.appcom.kmpplayground.game.databinding.ActivityGameBinding
import game.domain.Game
import game.presentation.GameView
import javax.inject.Inject

class GameActivity : DaggerAppCompatActivity(), GameView {

  @Inject
  lateinit var game: Game

  lateinit var binding: ActivityGameBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityGameBinding.inflate(layoutInflater)
    setContentView(binding.root)
    setUpToolbar()
  }

  private fun setUpToolbar() {
    setSupportActionBar(binding.gameToolbar)
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

  private fun setUpGame() {
    forEachGameButton { childButton, index ->
      childButton.setOnClickListener {
        game.onNewStonePlaced(index)
      }
    }
  }

  private fun forEachGameButton(action: (childButton: View, index: Int) -> Unit) {
    for (i in 0 until binding.gameGridlayout.childCount) {
      action(binding.gameGridlayout.getChildAt(i), i)
    }
  }

  override fun invalidateBoard(board: Array<Int>) {
    forEachGameButton { button, index ->
      button.setBackgroundColor(
        when (board[index]) {
          -1 -> Color.parseColor(GameUtils.colorPlayer1)
          1 -> Color.parseColor(GameUtils.colorPlayer2)
          else -> Color.parseColor(GameUtils.colorClear)
        }
      )
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