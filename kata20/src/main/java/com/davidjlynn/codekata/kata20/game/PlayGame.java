package com.davidjlynn.codekata.kata20.game;

import com.davidjlynn.codekata.kata20.game.components.Move;
import java.util.List;

public class PlayGame {

  public GameState play(GameState gameState, PlayMode playMode) {
    switch (playMode) {
      case DUMB_SEQUENTIAL:
        return playDumbSequential(gameState);
      default:
        throw new IllegalStateException("Invalid Game State.");
    }
  }

  private GameState playDumbSequential(GameState gameState) {
    // Always use first move, limit 300
    for (int i = 0; i < 300; i++) {

      if (gameState.hasWon()) {
        break;
      }

      List<Move> moveList = gameState.getPossibleMoves();

      if (moveList.size() == 0) {
        break;
      }

      Move move = moveList.get(0);

      gameState.doMove(move);
    }

    return gameState;
  }
}
