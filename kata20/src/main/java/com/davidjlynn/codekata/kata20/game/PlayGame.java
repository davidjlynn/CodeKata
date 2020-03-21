package com.davidjlynn.codekata.kata20.game;

import com.davidjlynn.codekata.kata20.game.components.Move;
import java.util.List;
import java.util.Random;

public class PlayGame {

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";

  public GameState play(GameState gameState, PlayMode playMode) {
    switch (playMode) {
      case DUMB_SEQUENTIAL:
        return playDumbSequential(gameState);
      case RANDOM_MOVE:
        return playRandom(gameState);
      case ORDER_BY_DIRECTION:
        return orderedByDirection(gameState);
      default:
        throw new IllegalStateException("Invalid Game State.");
    }
  }

  private GameState playDumbSequential(GameState gameState) {
    // Always use first move, limit 300
    for (int i = 0; i < 2000; i++) {

      if (gameState.hasWon()) {
        System.out.println(ANSI_GREEN + "Took " + i + 1 + "Attempts" + ANSI_RESET + " to win");
        break;
      }

      List<Move> moveList = gameState.getPossibleMoves();

      if (moveList.size() == 0) {
        System.out.println(ANSI_RED + "No more moves left!" + ANSI_RESET);
        break;
      }

      Move move = moveList.get(0);

      gameState.doMove(move);
    }
    System.out.println(ANSI_RED + "Ran out of attempts!" + ANSI_RESET);

    return gameState;
  }

  private GameState playRandom(GameState gameState) {
    // Always use first move, limit 300
    for (int i = 0; i < 3000; i++) {

      if (gameState.hasWon()) {
        System.out.println(ANSI_GREEN + "Took " + i + 1 + "Attempts" + ANSI_RESET + " to win");
        break;
      }

      List<Move> moveList = gameState.getPossibleMoves();

      if (moveList.size() == 0) {
        System.out.println(ANSI_RED + "No more moves left!" + ANSI_RESET);
        break;
      }
      Integer moveNumber = new Random().nextInt(moveList.size());
      Move move = moveList.get(moveNumber);

      gameState.doMove(move);
    }
    List<Move> leftOverMoveList = gameState.getPossibleMoves();
    System.out.println(ANSI_RED + "Ran out of attempts!" + ANSI_RESET);

    return gameState;
  }

  private GameState orderedByDirection(GameState gameState) {
    // Always use first move, limit 300
    for (int i = 0; i < 3000; i++) {

      if (gameState.hasWon()) {
        System.out.println(ANSI_GREEN + "Took " + i + 1 + "Attempts" + ANSI_RESET + " to win");
        break;
      }

      List<Move> moveList = gameState.getPossibleMoves();

      // ORDER MOVES
      // 1. To finished pile from Deck
      // 2. To finished pile from Play
      // 3. Random play piles
      // 4. Deck to play
      // 5. Flip
      // 6. Finished
       moveList.sort((o1, o2) -> toDirected(o1).compareTo(toDirected(o2)));

      if (moveList.size() == 0) {
        System.out.println(ANSI_RED + "No more moves left!" + ANSI_RESET);
        break;
      }
      Integer moveNumber = new Random().nextInt(moveList.size());
      Move move = moveList.get(moveNumber);

      gameState.doMove(move);
    }
    List<Move> leftOverMoveList = gameState.getPossibleMoves();
    System.out.println(ANSI_RED + "Ran out of attempts!" + ANSI_RESET);

    return gameState;
  }

  private Integer toDirected(Move move){
    switch (move.getSource().getPileType()){
      case DECK:
        switch (move.getDestination().getPileType()){
          case FINISHED_PILE: return 1;
          case PLAY_PILE: return 4;
        }
      case PLAY_PILE:
        switch (move.getDestination().getPileType()){
          case FINISHED_PILE: return 2;
          case PLAY_PILE: return 3;
        }
      case FLIP_CARD:return 5;
      case FINISHED_PILE:return 6;
    }
    throw new IllegalStateException();
  }
}
