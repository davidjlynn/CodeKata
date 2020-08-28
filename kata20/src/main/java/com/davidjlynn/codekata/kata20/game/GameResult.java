package com.davidjlynn.codekata.kata20.game;

import lombok.Getter;

@Getter
public class GameResult {
  private final GameStatus gameStatus;
  private final Integer numberOfMoves;

  public GameResult(GameState gameState) {
    this.gameStatus = gameState.getGameStatus();
    this.numberOfMoves = gameState.getNumberOfMoves();
  }
}
