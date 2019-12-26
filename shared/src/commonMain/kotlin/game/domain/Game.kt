package game.domain

import game.domain.Game.Mode.Multiplayer
import game.domain.Game.Mode.SinglePlayer
import game.domain.Game.Player.Human1
import game.domain.Game.Player.Human2
import game.domain.Game.Player.KI2
import game.presentation.GameView
import kotlin.random.Random

class Game(
  val gameView: GameView
) {
  val mode: Mode = SinglePlayer
  private var players: Array<Player>
  private var activePlayerIndex = 0
  private var activePlayer: Player
    get() = players[activePlayerIndex]
    set(value) {
      if (value == Human1) {
        activePlayerIndex = 0
      } else if (value == Human2 || value == KI2) {
        activePlayerIndex = 1
      }
    }

  init {
    players = if (mode == SinglePlayer) {
      arrayOf(Human1, KI2)
    } else {
      arrayOf(Human1, Human2)
    }
    activePlayerIndex = 0
  }

  private var board: Array<Int> = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
  private var gameIsOver: Boolean = false

  private fun boardIsFreeAt(position: Int): Boolean {
    return board[position] == 0
  }

  private fun putStoneAt(board: Array<Int>, position: Int, player: Player) {
      board[position] = player.value
  }

  /**
   * checks if there are empty positions left
   */
  private fun boardIsFull(): Boolean = board.none { position -> position == 0 }

  /**
   * Checks if given player has won
   */
  private fun playerHasWon(board: Array<Int>, player: Player): Boolean {
    val horizontal = (board[0] + board[1] + board[2] == 3 * player.value) ||
        (board[3] + board[4] + board[5] == 3 * player.value) ||
        (board[6] + board[7] + board[8] == 3 * player.value)

    val vertical = (board[0] + board[3] + board[6] == 3 * player.value) ||
        (board[1] + board[4] + board[7] == 3 * player.value) ||
        (board[2] + board[5] + board[8] == 3 * player.value)

    val diagonal = (board[0] + board[4] + board[8] == 3 * player.value) ||
        (board[2] + board[4] + board[6] == 3 * player.value)

    return horizontal || vertical || diagonal
  }

  private fun winningPlayerIfExists(board: Array<Int>): Player? {
    return when {
      playerHasWon(board, players[0]) -> {
        players[0]
      }
      playerHasWon(board, players[1]) -> {
        players[1]
      }
      else -> null
    }
  }

  private fun freePositionIndexes(): List<Int> {
    val freePositions = mutableListOf<Int>()
    board.forEachIndexed { index, i ->
      if (i == 0) {
        freePositions.add(index)
      }
    }
    return freePositions
  }

  private fun kiPlayNextRound() {
    val freePositions = freePositionIndexes()
    onNewStonePlaced(freePositions[Random.nextInt(0, freePositions.size)])
  }

  private fun kiPlayNextRoundOptimized() {
    val freePositions = freePositionIndexes()

    var bestPosition = freePositions[Random.nextInt(0, freePositions.size)]
    // for each position
    if (freePositions.size > 1) {
      // for each possible position
      freePositions.forEach { possiblePosition ->
        // create a temporary board
        val boardInFuture = board.copyOf()
        // place a stone at the possible position
        putStoneAt(boardInFuture, possiblePosition, KI2)
        // provide a score for the position
        val winner = winningPlayerIfExists(boardInFuture)
        if (winner != null && winner == KI2) {
          bestPosition = possiblePosition
        }
      }
    }
    onNewStonePlaced(bestPosition)

    // make copy of the board
    // set stone
    // show if win
  }

  fun notifyUserWon(winner: Player) {
    val message = when {
      mode == SinglePlayer && winner == Human1 -> "You won the game"
      mode == SinglePlayer && winner == KI2 -> "You lost the game"
      mode == Multiplayer && winner == Human1 -> "Player blue won"
      else -> "Player orange won"
    }
    gameView.notify(message)
  }

  fun notifyBoardFull() {
    gameView.notify("Game over. Nobody won")
  }

  private fun nextPlayerIndex() = (activePlayerIndex + 1) % players.size

  fun onNewStonePlaced(newStonePosition: Int) {

    if (boardIsFreeAt(newStonePosition) && (!gameIsOver)) {

      // update board
      putStoneAt(board, newStonePosition, activePlayer)

      // invalidateBoard
      gameView.invalidateBoard(board)

      // check game is over
      val winner = winningPlayerIfExists(board)
      if (winner != null) {
        gameIsOver = true
        notifyUserWon(winner)
      } else if (boardIsFull()) {
        gameIsOver = true
        notifyBoardFull()
      } else {
        // next player
        activePlayerIndex = nextPlayerIndex()
        if (mode == SinglePlayer && activePlayer == KI2) {
          //kiPlayNextRound()
          kiPlayNextRoundOptimized()
        }
      }
    }
  }

  enum class Player(val value: Int) {
    Human1(1),
    Human2(-1),
    KI2(-1)
  }

  enum class Mode() {
    SinglePlayer,
    Multiplayer
  }
}