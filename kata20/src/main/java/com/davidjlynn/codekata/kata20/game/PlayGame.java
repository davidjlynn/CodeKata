package com.davidjlynn.codekata.kata20.game;

public class PlayGame {

  public GameState play(GameState gameState, PlayMode playMode) {
    switch (playMode){
      case DUMB_SEQUENTIAL:
        return gameState;
      default:
        throw new IllegalStateException("Invalid Game State.");
    }
  }
}
