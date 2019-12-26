package de.appcom.kmpplayground.di

import dagger.Module
import dagger.Provides
import de.appcom.kmpplayground.presentation.GameActivity
import nasa.game.domain.Game
import nasa.game.presentation.GameView

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