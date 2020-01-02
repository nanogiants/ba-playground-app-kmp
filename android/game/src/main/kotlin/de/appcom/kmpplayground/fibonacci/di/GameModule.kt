package de.appcom.kmpplayground.fibonacci.di

import dagger.Module
import dagger.Provides
import de.appcom.kmpplayground.fibonacci.presentation.GameActivity
import game.domain.Game
import game.presentation.GameView

@Module
object GameModule {
  @JvmStatic
  @Provides
  fun provideGame(gameView: GameView): Game {
    return Game(gameView)
  }

  @Provides
  fun provideGameView(activity: GameActivity): GameView {
    return activity
  }
}