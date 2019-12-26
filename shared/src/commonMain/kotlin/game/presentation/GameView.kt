package nasa.game.presentation

interface GameView {
  fun invalidateBoard(board: Array<Int>)

  fun notify(message: String)
}