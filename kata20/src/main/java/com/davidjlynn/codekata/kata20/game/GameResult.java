package com.davidjlynn.codekata.kata20.game;

import lombok.Getter;

@Getter
public class GameResult {
  private GameStatus gameStatus;
  private Integer numberOfMoves;

  public GameResult(GameState gameState) {
    this.gameStatus = gameState.getGameStatus();
    this.numberOfMoves = gameState.getNumberOfMoves();
  }
}
